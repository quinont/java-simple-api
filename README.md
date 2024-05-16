# java-simple-api
Solo soy una api simple hecha en java


## Para probar

como se hace para agregar un item:
```
curl ${IP_DEL_SERVICIO}:8080/api/messages -X POST -H "Content-Type: text/plain" -d "ccccccdasdccc" | jq .
```

Para traer todos los mensajes:

```
curl ${IP_DEL_SERVICIO}:8080/api/messages   -H "Content-Type: text/plain" | jq .
```
