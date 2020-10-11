package com.epam.finaltack.ivanvertylo;

public class Path {

    private Path() { }

    public static final String CONTROLLER_MAIN_PAGE = "main";
    public static final String CONTROLLER_ADMIN_PAGE = "admin";
    public static final String CONTROLLER_EDITOR_PAGE = "editor";
    public static final String CONTROLLER_ERROR_PAGE = "error";
    public static final String CONTROLLER_TEST_PAGE = "test";


    public static final String MAIN_PAGE = "/index.jsp";
    public static final String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    public static final String EDITOR_PAGE = "/WEB-INF/jsp/editor.jsp";
    public static final String TEST_PAGE = "/WEB-INF/jsp/test.jsp";

    public static final String NO_REDIRECT = "noRedirect";
}
