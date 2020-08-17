package com.zxdc.utils.library.bean;

import java.io.File;
import java.io.Serializable;

public class FileBean implements Serializable {

    private String name;
    private File file;

    public FileBean(){}

    public FileBean(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
