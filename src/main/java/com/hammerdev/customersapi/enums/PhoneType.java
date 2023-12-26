package com.hammerdev.customersapi.enums;

public enum PhoneType
{
    CELULAR("celular"), COMERCIAL("comercial"), RESIDENCIAL("residencial");

    final String type;

    PhoneType (String type)
    {
        this.type = type;
    }

    public String getType() { return this.type; }
    
    @Override
    public String toString()
    {
        return getType();
    }
}
