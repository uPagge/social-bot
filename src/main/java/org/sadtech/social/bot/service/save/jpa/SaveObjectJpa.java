package org.sadtech.social.bot.service.save.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sadtech.social.core.domain.BasicEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [01/08/2019]
 */
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class SaveObjectJpa extends BasicEntity {

    @Column(name = "personId")
    private Long personId;

}
