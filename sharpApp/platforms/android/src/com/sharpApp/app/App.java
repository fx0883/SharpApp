package com.sharpApp.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

/*
 * Author: wangjie Email:wangj@bluemobi.sh.cn
 * Created Date:2014-10-15
 * Copyright @ 2014 BU
 * Description: 缁�缁�锟斤拷��╋拷
 *
 * History:
 */
public class App extends Application {

	/**
	 * ��达拷锟斤拷��ワ拷锟芥��锟界�电�����
	 */
	public static App app;;

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		File file = new File(getSDPath() + "/data.zip");
		if (!file.exists()) {
			copyfile();
			try {
				upZipFile1(file, getSDPath());
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
//			try {
//				// upZipFile(file, getSDPath());
//			//	upZipFile1(file, getSDPath());
//			} catch (ZipException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 锟斤拷���锟斤拷sd锟斤拷���锟斤拷锟斤拷锟界��锟斤拷锟斤拷
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 锟斤拷宄帮拷锟界�猴拷锟斤拷锟借ぐ锟�
		}
		return sdDir.toString();
	}

	public void copyfile() {
		String path = "data.zip";
		// File tofire = new File(Environment.getExternalStorageDirectory() + "/base/ic_launcher-web.png");
		File tofire = new File(Environment.getExternalStorageDirectory() + "/" + path);
		if (!tofire.getParentFile().exists()) {
			tofire.getParentFile().mkdirs();
		}

		try {
			String fileName = path;
			InputStream fosfrom = getResources().getAssets().open(fileName);

			FileOutputStream fosto = new FileOutputStream(tofire);

			byte[] bt = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception e) {
			Log.e("Exception", e.toString());
		}
	}

	/**
	 * ���锝�锟斤拷缂����锟斤拷娑�锟斤拷锟斤拷娴�锟�
	 * 
	 * @param zipFile
	 *            锟斤拷锟界�����锟斤拷娴�锟�
	 * @param folderPath
	 *            ���锝�锟斤拷缂����锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟借ぐ锟�
	 * @throws IOException
	 *             瑜帮拷���锝�锟斤拷缂����锟斤拷缁�锟斤拷锟芥椽锟斤拷锟斤拷��碉拷锟斤拷锟斤拷
	 */
	public static void upZipFile(File zipFile, String folderPath) throws ZipException, IOException {
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			desDir.mkdirs();
		}
		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			InputStream in = zf.getInputStream(entry);
			String str = folderPath + File.separator + entry.getName();
			str = new String(str.getBytes("8859_1"), "GB2312");
			File desFile = new File(str);
			if (!desFile.exists()) {
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) {
					fileParentDir.mkdirs();
				}
				desFile.createNewFile();
			}
			OutputStream out = new FileOutputStream(desFile);
			byte buffer[] = new byte[1024 * 1024];
			int realLength;
			while ((realLength = in.read(buffer)) > 0) {
				out.write(buffer, 0, realLength);
			}
			in.close();
			out.close();
		}
	}

	public int upZipFile1(File zipFile, String folderPath) throws ZipException, IOException {
		// public static void upZipFile() throws Exception{
		ZipFile zfile = new ZipFile(zipFile);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				Log.d("upZipFile", "ze.getName() = " + ze.getName());
				String dirstr = folderPath + ze.getName();
				// dirstr.trim();
				dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
				Log.d("upZipFile", "str = " + dirstr);
				File f = new File(dirstr);
				f.mkdir();
				continue;
			}
			Log.d("upZipFile", "ze.getName() = " + ze.getName());
			OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
		Log.d("upZipFile", "finishssssssssssssssssssss");
		return 0;
	}

	/**
	 * 缂�锟界�癸拷锟斤拷���锟斤拷瑜帮拷���锟芥�╋拷锟斤拷锟芥��锟芥��锟斤拷锟界�匡拷纭���惧�帮拷锟斤拷锟界�电�帮拷锟斤拷锟斤拷��癸拷锟斤拷锟斤拷锟斤拷娴�璺猴拷锟�.
	 * 
	 * @param baseDir
	 *            锟斤拷锟界�癸拷锟斤拷���锟斤拷瑜帮拷
	 * @param absFileName
	 *            锟斤拷绋匡拷纭���惧�帮拷锟斤拷锟介��锟斤拷锟姐��锟斤拷娴�锟�ZipEntry娑�锟斤拷锟斤拷name
	 * @return java.io.File ��癸拷锟斤拷锟斤拷锟斤拷锟斤拷锟芥��锟�
	 */
	public static File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split("/");
		File ret = new File(baseDir);
		String substr = null;
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				substr = dirs[i];
				try {
					// substr.trim();
					substr = new String(substr.getBytes("8859_1"), "GB2312");

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ret = new File(ret, substr);

			}
			Log.d("upZipFile", "1ret = " + ret);
			if (!ret.exists())
				ret.mkdirs();
			substr = dirs[dirs.length - 1];
			try {
				// substr.trim();
				substr = new String(substr.getBytes("8859_1"), "GB2312");
				Log.d("upZipFile", "substr = " + substr);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ret = new File(ret, substr);
			Log.d("upZipFile", "2ret = " + ret);
			return ret;
		}
		return ret;
	}
}
