package com.capitanperegrina.common.web;

import org.springframework.web.multipart.MultipartFile;
 
public class FileForm 
{
    MultipartFile file;
 
    public MultipartFile getFile() 
    {
        return file;
    }
 
    public void setFile(MultipartFile file) 
    {
        this.file = file;
    } 
}