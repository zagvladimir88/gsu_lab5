package org.example;

import org.example.dao.DirectoryDAO;
import org.example.dao.FileDAO;
import org.example.model.Directory;
import org.example.model.File;
import org.example.service.DirectoryService;
import org.example.service.FileService;
import org.hibernate.SessionFactory;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        DirectoryDAO directoryDAO = new DirectoryDAO(sessionFactory);
        FileDAO fileDAO = new FileDAO(sessionFactory);
        DirectoryService directoryService = new DirectoryService(directoryDAO, fileDAO,sessionFactory);
        FileService fileService = new FileService(fileDAO);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Directory");
            System.out.println("2. Add File");
            System.out.println("3. View Directory");
            System.out.println("4. View File");
            System.out.println("5. Update Directory");
            System.out.println("6. Update File");
            System.out.println("7. Delete Directory");
            System.out.println("8. Delete File");
            System.out.println("9. Get Full Path");
            System.out.println("10. Count Files in Directory");
            System.out.println("11. Calculate Disk Space");
            System.out.println("12. Find Files by Mask");
            System.out.println("13. Move Files and Subdirectories");
            System.out.println("14. Delete Files and Directories");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter directory name:");
                    String dirName = scanner.nextLine();
                    Directory parentDir = null;
                    System.out.println("Enter parent directory ID (0 for no parent):");
                    int parentId = scanner.nextInt();
                    if (parentId != 0) {
                        parentDir = directoryService.getDirectory(parentId);
                    }
                    Directory newDir = new Directory(parentDir, dirName);
                    directoryService.addDirectory(newDir);
                    break;
                case 2:
                    System.out.println("Enter file name:");
                    String fileName = scanner.nextLine();
                    System.out.println("Enter file size:");
                    int fileSize = scanner.nextInt();
                    System.out.println("Enter directory ID:");
                    int dirId = scanner.nextInt();
                    Directory directory = directoryService.getDirectory(dirId);
                    File newFile = new File(directory, fileName, fileSize);
                    fileService.addFile(newFile);
                    break;
                case 3:
                    System.out.println("Enter directory ID:");
                    int viewDirId = scanner.nextInt();
                    Directory viewDir = directoryService.getDirectory(viewDirId);
                    System.out.println("Directory: " + viewDir.getName());
                    break;
                case 4:
                    System.out.println("Enter file ID:");
                    int viewFileId = scanner.nextInt();
                    File viewFile = fileService.getFile(viewFileId);
                    System.out.println("File: " + viewFile.getName() + ", Size: " + viewFile.getSize());
                    break;
                case 5:
                    System.out.println("Enter directory ID:");
                    int updateDirId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter new directory name:");
                    String newDirName = scanner.nextLine();
                    Directory updateDir = directoryService.getDirectory(updateDirId);
                    updateDir.setName(newDirName);
                    directoryService.updateDirectory(updateDir);
                    break;
                case 6:
                    System.out.println("Enter file ID:");
                    int updateFileId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter new file name:");
                    String newFileName = scanner.nextLine();
                    System.out.println("Enter new file size:");
                    int newFileSize = scanner.nextInt();
                    File updateFile = fileService.getFile(updateFileId);
                    updateFile.setName(newFileName);
                    updateFile.setSize(newFileSize);
                    fileService.updateFile(updateFile);
                    break;
                case 7:
                    System.out.println("Enter directory ID:");
                    int deleteDirId = scanner.nextInt();
                    directoryService.deleteDirectory(deleteDirId);
                    break;
                case 8:
                    System.out.println("Enter file ID:");
                    int deleteFileId = scanner.nextInt();
                    fileService.deleteFile(deleteFileId);
                    break;
                case 9:
                    System.out.println("Enter directory ID:");
                    int pathDirId = scanner.nextInt();
                    String fullPath = directoryService.getFullPath(pathDirId);
                    System.out.println("Full path: " + fullPath);
                    break;
                case 10:
                    System.out.println("Enter directory ID:");
                    int countDirId = scanner.nextInt();
                    int fileCount = directoryService.countFilesInDirectory(countDirId);
                    System.out.println("Number of files in directory: " + fileCount);
                    break;
                case 11:
                    System.out.println("Enter directory ID:");
                    int sizeDirId = scanner.nextInt();
                    int diskSpace = directoryService.calculateDiskSpace(sizeDirId);
                    System.out.println("Total disk space used: " + diskSpace + " bytes");
                    break;
                case 12:
                    System.out.println("Enter directory ID:");
                    int maskDirId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter file mask:");
                    String mask = scanner.nextLine();
                    Set<File> files = directoryService.findFilesByMask(maskDirId, mask);
                    System.out.println("Files matching the mask:");
                    for (File file : files) {
                        System.out.println(file.getName());
                    }
                    break;
                case 13:
                    System.out.println("Enter source directory ID:");
                    int sourceDirId = scanner.nextInt();
                    System.out.println("Enter target directory ID:");
                    int targetDirId = scanner.nextInt();
                    directoryService.moveFilesAndSubdirectories(sourceDirId, targetDirId);
                    System.out.println("Files and subdirectories moved successfully.");
                    break;
                case 14:
                    System.out.println("Enter directory ID:");
                    int delDirId = scanner.nextInt();
//                    directoryService.deleteFilesAndSubdirectories(delDirId);
                    directoryDAO.deleteDirectoryWithNativeQuery(delDirId);
                    System.out.println("Files and subdirectories deleted successfully.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sessionFactory.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}