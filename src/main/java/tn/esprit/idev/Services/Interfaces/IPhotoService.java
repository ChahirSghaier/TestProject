package tn.esprit.idev.Services.Interfaces;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.idev.Entities.Photo;

import java.io.IOException;

public interface IPhotoService {
    public Photo uploadImage(MultipartFile file) throws IOException ;
    public byte[] downloadImage(String fileName) ;
    }
