package com.example.taskandpresent2.event.model;

/*
public class ImageMapper {
    public static Image toImage(ImageDto imageDto) {
        return new Image(imageDto.getId(),
                imageDto.getName(),
                imageDto.getDescription(),
                imageDto.getSize(),
                imageDto.getBytes(),
                EventMapper1.INSTANCE.toEvent(imageDto.getEventDto()));
    }

    public static ImageDto toImageDto(Image image) {
        return new ImageDto(image.getId(),
                image.getName(),
                image.getDescription(),
                image.getSize(),
                image.getBytes(),
                EventMapper1.INSTANCE.toEventDto(image.getEvent()));
    }

    public static ImageDto toImageEntity(MultipartFile file) throws IOException {
        ImageDto imageDto = new ImageDto();
        imageDto.setName(file.getName());
        imageDto.setSize(file.getSize());
        imageDto.setBytes(file.getBytes());
        return imageDto;
    }

}
*/