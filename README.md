# Social Bot

Этот модуль представляет собой абстрактную реализацию SDK, основываясь на которую можно разработать бота для любой 
социальной сети или мессенджера. В этом проекте реализована основная логика работы бота, остается реализовать только 
точки входа и выхода для конкретной реализации соц сети, то есть отправку и прием сообщений.

## Официальные реализации

На основе этой библиотеки были написаны следующие SDK для ботов:

1. [SDK для ботов ВКонтакте](https://github.com/uPagge/vk-bot)
2. [SDK для ботов Telegram]()

## Dependency

```
<dependency>
    <groupId>org.sadtech.social</groupId>
    <artifactId>social-bot</artifactId>
    <version>${social.bot.ver}</version>
</dependency>
```

## Программная реализация

Объект класса `GeneralAutoResponder` реализует основную логику, используя модуль 
[Autoresponder](https://github.com/uPagge/autoresponder).
Для общего понимания, сначала расмотрите логику работы модуля _Auoresponder_. В данном же модуле представлены 
наследники класса `Unit`.

Так же в данном модуле используются наработки проекта [Social Core](https://github.com/uPagge/social-core).

Даже если, вы бегло изучили документацию `Autoresponder`, то имеете понимание, что такое Unit. В данном проекте идея 
юнитов расширяется и дополняется. Появляется еще один адстрактный юнит - `MainUnit`, от которого наследуются уже 
конкретные реализации.

- `MainUnit` - абстрактный класс родитель для всех остальных наследников класса `Unit`. Его поля:
    - `String uuid` - Идентификатор для технических нужд, нет возможности изменить.
    - `UnitActiveType` - Режим срабатывания обработки юнита. Имеет два возможных варианта:  
        - `DEFAULT` - Обычный режим, устанавливается всем юнитам по умолчанию. Обработка юнита происходит после его 
        определения на обработку.  
        - `AFTER` - Обработка юнита начинается сразу же после обработки предыдущего юнита, не дожидаясь следующего 
        сообщения пользователя.
    - `String type` - Тип юнита, используется для идентификации обработчика юнита.
    - `Set<String> keyWords` - Ключевевые слова.
    - `String phrase` - Точная фраза.
    - `Pattern pattern` - Регулярное выражение.
    - `Integer matchThreshold` - Значение минимального отношения количества найденых ключевых слов, к количеству 
    ключевых слов Unit-а.
    - `Integer priority` - Значение приоритета.
    - `Set<MainUnit> nextUnits` - Множество следующих Unit в сценарии.
    
У конкретных реализаций есть обработчики, реализующие интерфейс `ActionUnit`. Обработчики выполняют некие манипуляции с 
юнитами. Таким образом **юнит сам ничего не делает**, являясь по сути только хранилищем для данных, с которыми работает 
обработчик.
    
Далее мы рассморим всех наследников `MainUnit`. Все примеры будут проилюстрированы с использованием 
[конкретной реализации данной библиотеки для ВКонтакте](https://github.com/uPagge/vk-bot).
Конфигурацию этих примеров можно посмотреть и запустить в [этом проекте](https://github.com/uPagge/simple-vkbot/).

### AnswerText

Используется для простого ответа пользователю.

Поля класса:
    - `BoxAnswer` - Объект, содержащий информацию, которую необходимо отправить пользователю. Его поля:
        - `String message` - Текстовое сообщение
        - `GeoCoordinate` - Географические координаты, используются если необходимо отправить карту с меткой.
        - `Integer stickerId` - Идентификатор стикера или смайла.
    - `Insert` - Функциональный интерфейс, который используется для замены маркеров (`{n}`) в сообщении `message` 
    на слова.
    - `KeyBoard` - Клавиатура, которую необходимо отобразить пользователю.
    - `Sending` - Интерфейс, реализация которого предполагает отправку ответа пользователя. В данном классе используется 
    для нестандартной отправки. Например пользователь написал сообщение в комментарии к посту, а ответ получил в личных 
    сообщениях.

#### Возможная конфигурация

```
@Bean
public AnswerText hello(AnswerValidity map, AnswerText nothing) {
    return AnswerText.builder()
            .boxAnswer(
                BoxAnswer.builder()
                    .message("Привет, %firstname%! Чем могу помочь")
                    .stickerId(21)
                    .keyBoard(
                        KeyBoards.verticalMenuString("Как до вас доехать?", "Ничем")
                    )
                .build()
            )
            .nextUnit(map)
            .nextUnit(nothing)
            .build();
}

@Bean
public AnswerText nothing() {
    return AnswerText.builder().boxAnswer(BoxAnswer.of("Очень жаль :(")).build();
}
```

#### Результат

Каждое сообщение от бота - это объект класса `AnswerText`.

![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img1.jpg)

Так же здесь демонстрируется возможность подстановки публичных данных пользователя, вместо ключевых слов. Например, 
`%firstname%` заменяется на имя собеседника бота. Это возможность не реализована в данной библиотеке, так как она не 
связана ни с какой социальной сетью. 

Но есть похожая механика в `AnswerText`, заключенная в поле `Insert`, которая заменяет маркер `{n}` на элемент коллекции
`Set<String>`, где `n` - целое число, начиная с нуля.

### AnswerSave

Используется для сохранения присланого сообщения пользователя. Можно использовать, чтобы сохранять анкеты и формы 
обратной связи. Его поля:
    - `Preservable` - Интерфейс реализация которого сохраняет сообщения пользователя в репозиторий, а так же получение 
    сохраненных ранее данных и отправку их на дальнейшую обработку.
    - `String key` - Идентификатор, который будет присвоен сохраненной информации.
    - `Pusher` - Интерфейс, реализация которого отправляет сохраненную форму пользователя на дальнейшую обработку, 
    используется в `Preservable`.
    - `PreservableData` - Используется для скрытого сохранения информации о пользователе, которая и так доступна, 
    без взаимодействия с пользователем.
    - `boolean hidden` - Флаг, означающий скрытое сохранение сообщения. По умолчанию `false`.
    - `CheckSave` - 
    
#### Конфигурация

```
code
```

#### Результат
 
![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img2.jpg)
 
Здесь более сложная логика. Сообщения от бота на картинке - это `AnswerText`. `AnswerSave` же выполняется сразу же 
после ответа пользователя, вызывая после себя следующий юнит, в данном случае `AnswerText`.
 
![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img3.jpg)
 
Это же результат работы реализации интерфейса `Pusher`, который был указан в последнем `AnswerSave` и вызывал отправку 
сообщения на указаный Email.

### AnswerValidity

Запршивает у пользователя подверждения истиности его публичных данных. Например, у пользователя на странице указан 
город Москва, данный юнит уточняет правдивость этой информации, а следом `AnswerSave` сохраняет город пользователя 
в анкету. Поля класса:
- `MainUnit unitYes` - Юнит отправляется на обработку, если пользователь подтвердил данные.
- `MainUnit unitNo` - Юнит отправляется на обработку, если пользователь не согласился со своими данными.
- `MainUnit unitNull` - Юнит обрабатывается, если данные не выложены в публичный доступ. Например пользователь 
скрыл свой город.
    
#### Возможная конфигурация

```
AnswerValidity.builder()
    .clarificationQuestion(
        message -> vkApi.getUserCity(message.getPersonId().intValue())
            .map(
                s -> Clarification.builder()
                        .question(BoxAnswer.of("Ваш город " + s + "?"))
                        .value(s)
                        .build()
            ).get()
    )
    .unitNo(noCityText)
    .unitYes(checkCity)
    .unitNull(noCityText)
    .keyWord("добраться")
    .keyWord("доехать")
    .build();
```

#### Результат    

В данном примере у пользователя в профиле указан город Москва.
    
![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img4.jpg)

При первом обращении вызывается обработка `AnswerValidity`. Так же на примере ниже продемонстрирована клавиатура.

![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img5.jpg)

Если пользователь отвечает "Нет" (можно ответить и просто текстом), то вызывается обработка `unitNo`:

![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img6.jpg)

Если пользователь отвечает "Да", то вызывается обработка `unitYes`:

![](https://raw.githubusercontent.com/uPagge/images/master/img/social-bot/img8.jpg)

### AnswerCheck

Используется для проверки выполнения пользователем какого-то условия. Поля класса:

- `CheckData` - Интерфейс, реализация которого содержит в себе логику проверки пользователя.
- `MainUnit unitTrue` - Юнит, который будет обработан в случае успешного прохождения проверки.
- `MainUnit unitFalse` - Юнит, который будет обработан в случае отрицательного результата проверки.
- `AnswerTimer` - используется для отложенного запуска обработки юнита. Например, можно отправить текстовое сообщение 
с просьбой оставить отзыв о покупке не сразу, а через неделю. Поля класса:

#### Конфигурация

```
code
```

#### Результат

### AnswerAccount

Используется для проведения оплат пользователем. На данный момент находится в разработке и использование данного юнита 
не рекомендовано.

## Как написать свою реализацию?
        
Если вы хотите написать бота например для Viber, или Facebook. Вы можете использовать эту библиотеку, 
для этого вам необходимо:

1. Реализовать интерфейс `Sending`, который отвечает за отправку сообщений пользователю.
2. 

