package com.rayonit.fleetmanager.domain.driver.service.implementations;

import com.rayonit.fleetmanager.domain.driver.service.interfaces.ImageConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageConverterImpl implements ImageConverter {
    @Override
    public String convert(MultipartFile file) {
        try{
            File upload = upload(file);
            return convertToString(upload);
        }catch (Exception e){
            System.out.println("Message IS:" + e.getMessage());
            return null;
        }
    }

    static public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    static public File upload(MultipartFile file){
        try{
            String extension = getExtensionByStringHandling(file.getOriginalFilename()).orElse(null);
            if (extension != null){
                String uniqueID = UUID.randomUUID().toString();
                File upload = new File("C:\\Users\\darze\\Documents\\Andi Darzeza\\fleetmanagementsystem\\fleet-manager\\src\\main\\java\\com\\rayonit\\fleetmanager\\domain\\driver\\driverimages\\" + uniqueID+"."+extension);
                if(upload != null){
                    upload.createNewFile();
                    FileOutputStream fout = new FileOutputStream(upload);
                    fout.write(file.getBytes());
                    fout.close();
                    return upload;
                }else return null;

            }else {
                return null;
            }

        }catch (Exception e){
            System.out.println("Message : " + e.getMessage());
            return null;
        }
    }

    static public String convertToString(File file){
        try{
            String encodeBase64 = null;
            String image = null;
            String extension = getExtensionByStringHandling(file.getName()).get();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStream.read(bytes);
            encodeBase64 = Base64.getEncoder().encodeToString(bytes);
            image = "data:image/" + extension + ";base64," + encodeBase64;
            fileInputStream.close();
            return image;
        }catch (Exception e){
            return null;
        }
    }
}
