package com.并发工具类;

public enum CountEnum {
    ONE(1, "齐国"), TWO(2, "楚国"), THREE(3, "燕国"),
    FOUR(4, "赵国"), FIVE(5, "魏国"), SIX(6, "韩国");
    private Integer id;
    private String name;

    CountEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public static CountEnum forEach_CuntryEnum(int index){
        CountEnum [] countEnums = CountEnum.values();
        for(CountEnum a:countEnums){
            if(index==a.id){
                return a;
            }
        }
        return null;
    }
}
