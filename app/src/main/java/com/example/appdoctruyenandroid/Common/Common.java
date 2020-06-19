package com.example.appdoctruyenandroid.Common;

import com.example.appdoctruyenandroid.Models.Category;
import com.example.appdoctruyenandroid.Models.Chapter;
import com.example.appdoctruyenandroid.Models.Comic;
import com.example.appdoctruyenandroid.Models.Page;
import com.example.appdoctruyenandroid.Models.TaiKhoanDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Common {
    public static Comic selected_comic;
    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
                                        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                                                "\\@" +
                                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                                "(" +
                                                "\\." +
                                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                                ")+"
                                                );
    public static final Pattern MATKHAU = Pattern.compile("^"+
                                                          "(?=.*[0-9])"+
                                                          "(?=.*[a-z])"+
                                                          "(?=.*[A-Z])"+
                                                          "(?=.*[!@#$%^&*(){}|\\'\";:?/>.<,])"+
                                                          "(?=\\S+$)"+
                                                          ".{8,}"+
                                                          "$");
    public static final Pattern TEN_DANG_NHAP = Pattern.compile("^"+
                                                            "(?=.*[0-9])"+
                                                            "(?=.*[a-z])"+
                                                            "(?=.*[A-Z])"+
                                                            "(?=.*[!@#$%^&*(){}])"+
                                                            "(?=\\S+$)"+
                                                            ".{8,}"+
                                                            "$");
    public static Chapter selected_chapter;
    public static TaiKhoanDto signin_TaiKhoan;
    public static int chapter_index = -1;

    public static List<Category> categories = new ArrayList<>();
}
