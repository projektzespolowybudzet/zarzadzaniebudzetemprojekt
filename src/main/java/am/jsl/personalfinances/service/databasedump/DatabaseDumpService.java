package am.jsl.personalfinances.service.databasedump;

/**
*Interfejs serwisowy określający sposób usunięcia bazy danych do wskazanego folderu.
*/
public interface DatabaseDumpService {

/**
*Usówa bazę danych do określonego folderu i kompresuje plik zrzutu.
*/
	void dumpDatabase();
}
