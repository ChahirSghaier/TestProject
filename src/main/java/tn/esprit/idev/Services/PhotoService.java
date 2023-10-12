package tn.esprit.idev.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.idev.Entities.Photo;
import tn.esprit.idev.Repositories.PhotoRepository;
import tn.esprit.idev.Services.Interfaces.IPhotoService;
import tn.esprit.idev.util.FileUploader;

import java.io.IOException;
import java.util.Optional;
@Service
public class PhotoService implements IPhotoService {
    @Autowired
    private PhotoRepository imageRepo;

    public Photo uploadImage(MultipartFile file) throws IOException {
        Photo pImage = new Photo();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(FileUploader.compressImage(file.getBytes()));
        return imageRepo.save(pImage);
    }

    public byte[] downloadImage(String fileName){
        Optional<Photo> imageData = imageRepo.findPhotoByName(fileName);
        return FileUploader.decompressImage(imageData.get().getImageData());
    }



}
