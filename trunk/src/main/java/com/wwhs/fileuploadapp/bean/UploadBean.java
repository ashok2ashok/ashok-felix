package com.wwhs.fileuploadapp.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author lenovo
 */
public class UploadBean {
private CommonsMultipartFile filedata;


    public CommonsMultipartFile getFiledata() {
        return filedata;
    }

    public void setFiledata(CommonsMultipartFile filedata) {
        this.filedata = filedata;
    }

    

}
