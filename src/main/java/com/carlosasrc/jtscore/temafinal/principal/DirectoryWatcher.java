package com.carlosasrc.jtscore.temafinal.principal;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.carlosasrc.jtscore.temafinal.dao.DataAnalysisDao;
import com.carlosasrc.jtscore.temafinal.util.Constants;

public class DirectoryWatcher {

	private static DirectoryWatcher instance;
	private WatchService watchService;
	private DataAnalysis dataAnalysis;
	private Thread watchServiceThread;

	public DirectoryWatcher() {}

	public static synchronized DirectoryWatcher getInstance() {
		if (instance == null) {
			instance = new DirectoryWatcher();
		}
		return instance;
	}

	public void start(String path) {
		watchServiceThread = new Thread(new Runnable() {
			public void run() {
				watchDirectory(path);
			}
		});
		watchServiceThread.start();
	}

	public void watchDirectory(String pathName) {
		try {
			dataAnalysis = DataAnalysis.getInstance();
			DataAnalysisDao.getInstance();

			watchService = FileSystems.getDefault().newWatchService();

			Path path = Paths.get(pathName);
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey key;
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					if (event.context().toString().matches(Constants.FILE_NAME_VALIDATOR.value)) {
						if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
							System.out.println("\nFile deleted: " + event.context());
						} else if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
							System.out.println("\nFile created/modified: " + event.context());
						}
						dataAnalysis.refreshData();
					}
				}
				key.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}