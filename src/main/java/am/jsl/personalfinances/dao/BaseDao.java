package am.jsl.personalfinances.dao;

import java.util.List;

/**
*Podstawowy interfejs DAO do interakcji z obiektami domeny Entity w sposób ogólny.
*@param <T> typ jednostki parametryzacji.
*/
public interface BaseDao<T> {
/**
*Zwraca wszystkie wystąpienia typu dla danego użytkownika.
*@param userId identyfikator użytkownika powiązany z jednostką
*@return wyniki
*/
    List<T> list(long userId);

/**
*Zwraca, czy podmiot o podanym identyfikatorze i identyfikatorze użytkownika może zostać usunięty.
*@param id identyfikator jednostki
*@param userId identyfikator użytkownika powiązany z jednostką
*@return true, jeśli obiekt o podanym identyfikatorze i identyfikatorze użytkownika może zostać usunięty
*/
    boolean canDelete(long id, long userId);

/**
*Usuwa podmiot o podanym identyfikatorze i identyfikatorze użytkownika.
*@param id identyfikator jednostki
*@param userId identyfikator użytkownika powiązany z jednostką
*/
    void delete(long id, long userId);

/**
*Zwraca, czy istnieje podmiot o podanym identyfikatorze i identyfikatorze użytkownika.
*@param nazwa nazwa jednostki
*@param id identyfikator jednostki
*@param userId identyfikator użytkownika powiązany z jednostką
*@return true jeśli jednostka o podanym identyfikatorze i identyfikatorze użytkownika istnieje
*/
    boolean exists(String name, long id, long userId);

/**
*Tworzy dany podmiot.
*@param obiekt obiekt
*/
    void create(T object);

/**
*Aktualizuje daną jednostkę.
*@param obiekt obiekt
*/
    void update(T object);

/**
*Pobiera podmiot według jego identyfikatora i identyfikatora użytkownika.
*@param id identyfikator jednostki
*@param userId identyfikator użytkownika powiązany z jednostką
*@return obiekt
*/
    T get(long id, long userId);

/**
*Zwraca wszystkie wystąpienia typu powiązanego z podanym identyfikatorem użytkownika.
*Zostanie zapytany o identyfikator i nazwę instancji.
*@param userId identyfikator użytkownika powiązany z jednostką
*@return wynik
*/
    List<T> lookup(long userId);
}
