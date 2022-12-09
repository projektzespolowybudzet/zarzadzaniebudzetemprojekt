package am.jsl.dolarek.service;

import java.util.List;

import am.jsl.dolarek.ex.CannotDeleteException;

/**
 *Interfejs usługi, który definiuje wszystkie metody pracy z obiektami domeny w ogólny sposób.
 *@param <T> typ jednostki parametryzacji.
 */
public interface BaseService <T> {
    /**
     *Zwraca wszystkie wystąpienia typu dla danego użytkownika.
     *@param userId identyfikator użytkownika powiązany z jednostką
     *@return wyniki
     */
    List<T> list(long userId);

    /**
     *Usuwa obiekt o podanym identyfikatorze i identyfikatorze użytkownika.
     *Wyrzuci {@link CannotDeleteException}, jeśli podmiot nie może zostać usunięty.
     *@param id identyfikator jednostki
     *@param userId identyfikator użytkownika powiązany z jednostką
     *@throws CannotDeleteException, jeśli podmiot nie może zostać usunięty
     */
    void delete(long id, long userId) throws CannotDeleteException;

    /**
     *Zwraca, czy istnieje podmiot o podanym identyfikatorze i identyfikatorze użytkownika.
     *@param name nazwa jednostki
     *@param id identyfikator jednostki
     *@param userId identyfikator użytkownika powiązany z jednostką
     *@return true jeśli jednostka o podanym identyfikatorze i identyfikatorze użytkownika istnieje
     */
    boolean exists(String name, long id, long userId);

    /**
     *Tworzy dany podmiot.
     *@param object obiekt
     */
    void create(T object) throws Exception;

    /**
     *Aktualizuje daną jednostkę.
     *@param object obiekt
     */
    void update(T object) throws Exception;

    /**
     *Pobiera podmiot według jego identyfikatora i identyfikatora użytkownika.
     *@param id identyfikator jednostki
     *@param userId identyfikator użytkownika powiązany z jednostką
     *@return podmiot
     */
    T get(long id, long userId);

    /**
     *Zwraca wszystkie wystąpienia typu powiązanego z podanym identyfikatorem użytkownika.
     *Zostanie zapytany o identyfikator i nazwę instancji.
     *@param userId identyfikator użytkownika powiązany z jednostką
     *@return wynik
     */
    List<T> lookup(long userId);

    /**
     *Tworzy nowy plik o podanej nazwie i treści w katalogu publikowania użytkownika.
     *Jeśli plik o podanej nazwie istnieje, nadpisuje go.
     *Opublikowany plik jest reprezentacją HTML obiektów określonego typu
     *i ładowane bezpośrednio przez sieć przy użyciu identyfikatora użytkownika bez uderzania w bazę danych.
     *@param userId identyfikator użytkownika
     *@param fileName nazwa pliku
     *@param content zawartość pliku
     */
    void publish(long userId, String fileName, String content);
}
