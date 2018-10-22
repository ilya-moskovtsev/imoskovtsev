package ru.job4j.tracker;

import java.util.Objects;

/**
 * 2. Реализовать класс Tracker
 *
 * @author imoskovtsev
 */
public class Item {
    /**
     * id заявки.
     */
    private final String id;

    /**
     * имя заявки.
     */
    private String key;

    /**
     * имя ответственного.
     */
    private String name;

    /**
     * описание заявки.
     */
    private String desc;

    /**
     * дата создания.
     */
    private long createDate;

    /**
     * дата обновления.
     */
    private long updateDate;

    /**
     * Конструктор.
     *
     * @param id         id заявки
     * @param key        имя заявки
     * @param name       имя ответственного
     * @param desc       описание заявки
     * @param createDate дата создания
     * @param updateDate дата обновления
     */
    public Item(String id, String key, String name, String desc, long createDate, long updateDate) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.desc = desc;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    /**
     * Возвращает id.
     *
     * @return String id заявки
     */
    public String getId() {
        return this.id;
    }

    /**
     * Возвращает key.
     *
     * @return String имя заявки
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Возвращает name.
     *
     * @return String имя ответственного
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возвращает desc.
     *
     * @return String описание заявки
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Возвращает createDate.
     *
     * @return long дата создания
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Возвращает updateDate.
     *
     * @return long дата обновления
     */
    public long getUpdateDate() {
        return this.updateDate;
    }

    /**
     * Устанавливает key.
     *
     * @param key имя заявки.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Устанавливает name.
     *
     * @param name имя ответственного
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает desc.
     *
     * @param desc описание заявки
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Устанавливает createDate.
     *
     * @param createDate дата создания
     */
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    /**
     * Устанавливает updateDate.
     *
     * @param updateDate дата обновления
     */
    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
