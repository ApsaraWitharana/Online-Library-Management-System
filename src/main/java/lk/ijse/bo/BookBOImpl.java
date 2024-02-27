package lk.ijse.bo;

import lk.ijse.dao.BookDAO;
import lk.ijse.dao.BookDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookBOImpl implements BookBO{

    BookDAO bookDAO= (BookDAO) new BookDAOImpl();
    @Override
   public ArrayList<BookDTO> getAllBook() {
        ArrayList<Book> books = bookDAO.getAll();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : books) {
            bookDTOS.add(new BookDTO(book.getId(),book.getTitle(),book.getStream(),book.getAuthor(),book.getStatus(),book.getQty()));

        }
        return bookDTOS;
   }

   @Override
   public boolean saveBook(BookDTO dto) throws SQLException,ClassNotFoundException {
       return bookDAO.save(new Book(dto.getId(),dto.getTitle(),dto.getStream(),dto.getAuthor(),dto.getStatus(),dto.getQty()));
   }

   @Override
   public boolean updateBook(BookDTO dto) {
         return bookDAO.update(new Book(dto.getId(),dto.getTitle(),dto.getStream(),dto.getAuthor(),dto.getStatus(),dto.getQty()));
    }

    @Override
   public void deleteBook(int id){
        bookDAO.delete(id);

    }

    @Override
   public BookDTO searchBook(int id) {
       Book book = (Book) bookDAO.search(id);
       return new BookDTO(book.getId(),book.getTitle(),book.getStream(),book.getAuthor(),book.getStatus(),book.getQty());
    }
}
