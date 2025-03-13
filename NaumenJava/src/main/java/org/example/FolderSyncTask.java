package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicBoolean;

public class FolderSyncTask implements Task {
    private final Path sourceDir;
    private final Path targetDir;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public FolderSyncTask(String source, String target) {
        this.sourceDir = Path.of(source);
        this.targetDir = Path.of(target);
    }

    @Override
    public void start() {
        if (!running.compareAndSet(false, true)) {
            System.out.println("Синхронизация уже запущена.");
            return;
        }

        System.out.println("Синхронизация началась...");

        Thread syncThread = new Thread(this::syncFolders);
        syncThread.setDaemon(true);
        syncThread.start();
    }

    @Override
    public void stop() {
        running.set(false);
        System.out.println("Синхронизация остановлена.");
    }

    private void syncFolders() {
        try {
            if (Files.notExists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    copyFileOrDirectory(dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    copyFileOrDirectory(file);
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException e) {
            System.err.println("Ошибка при синхронизации: " + e.getMessage());
        }
    }

    private void copyFileOrDirectory(Path path) {
        try {
            Path relativePath = sourceDir.relativize(path);
            Path targetPath = targetDir.resolve(relativePath);

            if (Files.isDirectory(path)) {
                if (Files.notExists(targetPath)) {
                    Files.createDirectories(targetPath);
                    System.out.println("Создан каталог: " + targetPath);
                }
            } else {
                if (Files.notExists(targetPath.getParent())) {
                    Files.createDirectories(targetPath.getParent());
                }

                Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Скопирован файл: " + targetPath);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при копировании " + path + ": " + e.getMessage());
        }
    }
}
