package org.sadtech.social.bot.service.save.jpa;

import lombok.RequiredArgsConstructor;
import org.sadtech.social.bot.service.save.Preservable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [01/08/2019]
 */
@RequiredArgsConstructor
public abstract class PreservableJpa<R extends JpaRepository<D, Integer>, D extends SaveObjectJpa> implements Preservable<D> {

    protected final R jpaRepository;

    @Override
    public void save(Integer personId, D save) {
        save.setPersonId(personId);
        jpaRepository.save(save);
    }

}
