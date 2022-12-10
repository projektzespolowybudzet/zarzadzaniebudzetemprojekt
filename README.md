# Projekt Zespołowy - aplikacja do zarządzania budżetem "Dolarek"

![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Style/dolarek-logo-zip-file/png/logo-no-background.png)

https://dolarek2.herokuapp.com/dolarek/

## Opis

Celem projektu jest stworzenie darmowej aplikacji webowej, dostępnej zarówno w przeglądarkach stacjonarnych oraz mobilnych, która pomoże użytkownikowi w analizie wydatków oraz wpływów. Użytkownik będzie mieć możliwość dodawania kont, kategorii oraz wydatków i wpływów, a następnie przeprowadzania analizy transakcji, sporządzania różnych bilansów, miesięcznych, kwartalnych, rocznych.

## Użyta technologia

**Back End:**
Java:
+ <a href="https://spring.io/projects/spring-boot" target="_blank">Spring Boot</a>
+ <a href="https://spring.io/guides/gs/relational-data-access/" target="_blank">Spring JDBC</a>
+ <a href="https://www.thymeleaf.org/" target="_blank">Thymeleaf</a>

**Front End:**
Javascript:
+ <a href="https://spring.io/projects/spring-security" target="_blank">Spring Security</a>
+ <a href="http://getbootstrap.com/" target="_blank">Twitter Bootstrap</a>
+ <a href="http://ace.jeka.by/" target="_blank">Ace Admin</a>

**Baza Danych:**
* MySql

**Deployment**
* Heroku

## Wymagania:
* Java 11
* Mysql8


## Uruchomienie aplikacji
Uruchomić skrypty z folderu setup/db/:
 
    database_schema.sql 
	  inserts.sql
  
Zmienić odpowiednio konfiguracje w pliku support/HOME/config/
    
    application.properties 
    
Dodać zmienne środowiskowe:

    SPRING_CONFIG_LOCATION=/SCIEZKA_DO/HOME/config/
    SPRING_CONFIG_NAME=application    
    
## [Zobacz Wymagania](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Wymagania.pdf)
## [Zobacz Instrukcję Użytkowania](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Dokumentacja-Dokumentacja-u%C5%BCytkownika-systemu-informatycznego-Aplikacja-do-zarz%C4%85dzania-bud%C5%BCetem-domowym.pdf)
## Diagramy:
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Diagram%20bazy%20danych%20Dolarek.jpg)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Diagram%20przypadkow%20uzycia%20Dolarek.jpg)
## [Zobacz Szkice Interfejsu](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/tree/main/dokumentacja/Interfejs%20png)
### Przyłady szkiców:
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Ekran%20logowania.png)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Kokpit.png)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Transakcje%20-%20tabela.png)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Transakcje%20-%20nowa%20transakcja.png)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Konta.png)
![text](https://github.com/projektzespolowybudzet/zarzadzaniebudzetemprojekt/blob/main/dokumentacja/Interfejs%20png/Konta%20-%20dodaj.png)
