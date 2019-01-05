package cn.fulugame.admin.config.editor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class CustomStringEditor  extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) {
        if (StringUtils.isNotEmpty(text)) {
            String value = text;
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : null;
    }
}
