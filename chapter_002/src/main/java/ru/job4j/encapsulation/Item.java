package ru.job4j.encapsulation;

/**
 * 2. Реализовать класс Tracker
 * @author imoskovtsev
 */
class Item {
    /**
     * id заявки.
     */
    private String id;

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
     * @param id id заявки
     * @param key имя заявки
     * @param name имя ответственного
     * @param desc описание заявки
     * @param createDate дата создания
     * @param updateDate дата обновления
     */
    Item(String id, String key, String name, String desc, long createDate, long updateDate) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.desc = desc;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    /**
     * Возвращает id.
     * @return String id заявки
     */
    String getId() {
        return this.id;
    }

    /**
     * Возвращает key.
     * @return String имя заявки
     */
    String getKey() {
        return this.key;
    }

    /**
     * Возвращает name.
     * @return String имя ответственного
     */
    String getName() {
        return this.name;
    }

    /**
     * Возвращает desc.
     * @return String описание заявки
     */
    String getDesc() {
        return this.desc;
    }

    /**
     * Возвращает createDate.
     * @return long дата создания
     */
    long getCreateDate() {
        return this.createDate;
    }

    /**
     * Возвращает updateDate.
     * @return long дата обновления
     */
    long getUpdateDate() {
        return this.updateDate;
    }

    /**
     * Устанавливает key.
     * @param key имя заявки.
     */
    void setKey(String key) {
        this.key = key;
    }

    /**
     * Устанавливает name.
     * @param name имя ответственного
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает desc.
     * @param desc описание заявки
     */
    void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Устанавливает createDate.
     * @param createDate дата создания
     */
    void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    /**
     * Устанавливает updateDate.
     * @param updateDate дата обновления
     */
    void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

}
