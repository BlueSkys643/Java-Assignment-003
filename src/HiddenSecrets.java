package src;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;

public class HiddenSecrets {
    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) throws IOException {
        // Put your code to request a file path,
        // read in a string from System.in,
        // convert that string into A Path type using Paths class,
        // and call the getHiddenSecrets method to get the file's meta-data
        // HERE
        Path imagePath = Paths.get("images/OllieTheOtter.jpg");
        System.out.println(imagePath);
        Scanner fileScan = new Scanner(imagePath);
        String input = fileScan.nextLine();
        System.out.println("Scanner found: " + input);
        File filePath = new File(imagePath.toUri());
        getHiddenSecrets(filePath);
        Path screenShotPath = Paths.get("images/Screenshot.png");
        System.out.println(screenShotPath);
    }

}

/*
Look at the getHiddenSecrets method and identify the following parts:
What is the access modifier (public, private, protected)?
public

Is it a Class method or an object Instance method, how do you know?
i think it is a class method cause while working on this assignment i seen errors saying i needed a class

What is its return data-type?
i am very uncertain, strings i think. this assignment proved very difficult for me

Does it require any parameters, and if so, how many and of what type?
it requires a single file to scan

Scan line by line through the code and try to determine how it works?
brain hurt...

What is familiar to you?
there is an if statement, I've used tons of those before. looks like it checks for errors then tells the user what
caused the error.

What is not familiar to you?
most of it, the directories and tags. I think the tags are what got turned into strings and printed in the console
so I would think that the tags are the metadata but that is just me speculating.

Do the for loops make sense, and if so, tell me what you think they do?
kind of. first for loop goes through all the metadata, second one checks for errors and types to tell user what
went wrong. happened to me while working on this btw. proved very helpful in figuring out what was the issue.
 */