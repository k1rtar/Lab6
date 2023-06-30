# Lab6
## Разделить программу из лабораторной работы №5 на клиентский и серверный модули. Серверный модуль должен осуществлять выполнение команд по управлению коллекцией. Клиентский модуль должен в интерактивном режиме считывать команды, передавать их для выполнения на сервер и выводить результаты выполнения.

## Необходимо выполнить следующие требования:

Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений.<br/>
Объекты между клиентом и сервером должны передаваться в сериализованном виде.<br/>
Объекты в коллекции, передаваемой клиенту, должны быть отсортированы по размеру<br/>
Клиент должен корректно обрабатывать временную недоступность сервера.<br/>
Обмен данными между клиентом и сервером должен осуществляться по протоколу TCP<br/>
Для обмена данными на сервере необходимо использовать сетевой канал<br/>
Для обмена данными на клиенте необходимо использовать потоки ввода-вывода<br/>
Сетевые каналы должны использоваться в неблокирующем режиме.<br/>
## Обязанности серверного приложения:

Работа с файлом, хранящим коллекцию.<br/>
Управление коллекцией объектов.<br/>
Назначение автоматически генерируемых полей объектов в коллекции.<br/>
Ожидание подключений и запросов от клиента.<br/>
Обработка полученных запросов (команд).<br/>
Сохранение коллекции в файл при завершении работы приложения.<br/>
Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).<br/>
Серверное приложение должно состоять из следующих модулей (реализованных в виде одного или нескольких классов):<br/>
Модуль приёма подключений.<br/>
Модуль чтения запроса.<br/>
Модуль обработки полученных команд.<br/>
Модуль отправки ответов клиенту.<br/>
Сервер должен работать в однопоточном режиме.<br/>
## Обязанности клиентского приложения: 

Чтение команд из консоли.<br/>
Валидация вводимых данных.<br/>
Сериализация введённой команды и её аргументов.<br/>
Отправка полученной команды и её аргументов на сервер.<br/>
Обработка ответа от сервера (вывод результата исполнения команды в консоль).<br/>
Команду save из клиентского приложения необходимо убрать.<br/>
Команда exit завершает работу клиентского приложения.<br/>
### Важно!
Команды и их аргументы должны представлять из себя объекты классов. Недопустим обмен "простыми" строками. Так, для команды add или её аналога необходимо сформировать объект, содержащий тип команды и объект, который должен храниться в вашей коллекции.
