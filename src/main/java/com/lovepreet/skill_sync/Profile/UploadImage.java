package com.lovepreet.skill_sync.Profile;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Component
public class UploadImage {
    public String uploadImageToFreeImageAPI(MultipartFile imageFile) throws IOException {
        String apiKey = "6d207e02198a847aa98d0a2a901485a5"; // Replace with your FreeImage API key
        String freeImageUploadUrl = "https://freeimage.host/api/1/upload";

        // Prepare the multipart form data
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("key", apiKey);
        body.add("action", "upload");
        body.add("format", "json");
        body.add("source", new ByteArrayResource(imageFile.getBytes()) {
            @Override
            public String getFilename() {
                return imageFile.getOriginalFilename(); // Return the original filename
            }
        });

        // Create the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the HTTP entity with the body and headers
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Create RestTemplate to send the POST request
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send the POST request and get the response
            ResponseEntity<Map> response = restTemplate.exchange(freeImageUploadUrl, HttpMethod.POST, requestEntity, Map.class);

            // Extract the image URL from the response
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                Map<String, Object> imageData = (Map<String, Object>) responseBody.get("image");
                return (String) imageData.get("url"); // Return the image URL
            } else {
                throw new RuntimeException("Failed to upload image to FreeImage API");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading image", e);
        }
    }

}
