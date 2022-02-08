package domain.entities.fotos;

import spark.Request;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Helper {
    public static List<String> processImage(Request req, String imagesName, String path) throws IOException, ServletException {
        File uploadDir = new File(path);
        uploadDir.mkdir();

        Collection<Part> parts = req.raw().getParts();

        List<String> ret = new ArrayList<>();

        for(Part p : parts) {
            String name = p.getName();

            if (!name.contains(imagesName))
                continue;

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", ".jpg");
            InputStream input = req.raw().getPart(name).getInputStream();
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            ret.add(tempFile.getFileName().toString());
        }

        return ret;
    }
}
