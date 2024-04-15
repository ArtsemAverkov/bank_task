package by.ysl.banktask.common.utill;

public enum UserId {
    VALUE_1(1L),
    VALUE_2(2L);

    private final Long value;

    UserId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}


