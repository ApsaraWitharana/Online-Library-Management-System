package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;

import static java.awt.SystemColor.text;

public class AdminBOImpl implements AdminBO {

    AdminDAO adminDAO = new AdminDAOImpl();
    @Override
    public boolean updateAdminName(String text, String name) {
        return adminDAO.updateAdminName(text, name);

    }

    @Override
    public boolean deleteAdmin(String name) {
        return adminDAO.delete(name);
    }

    @Override
    public boolean updatePassword(String text, String name) {
        return adminDAO.updateName(text, name);
    }
}
