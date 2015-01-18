package com.model;

import java.util.Collection;

public class XmlHandler {

    public static String makeAutoCompleteSearchXml(Collection<Item> items) {
        StringBuilder stringBuilder = new StringBuilder("<items>");
        for(Item i : items) {
            stringBuilder.append("<item><id>");
            stringBuilder.append(i.getId());
            stringBuilder.append("</id><firstName>");
            stringBuilder.append(i.getFirstName());
            stringBuilder.append("</firstName><lastName>");
            stringBuilder.append(i.getLastName());
            stringBuilder.append("</lastName><description>");
            stringBuilder.append(i.getDescription());
            stringBuilder.append("</description><type>");
            stringBuilder.append(i.getDescription());
            stringBuilder.append("</type></item>");
        }
        stringBuilder.append("</items>");
        return stringBuilder.toString();
    }
}
