# Prune1 CRUD API

API REST CRUD Spring Boot inspiree du tutoriel JetBrains.

## Endpoints

- `GET /api/bookmarks`
- `GET /api/bookmarks/{id}`
- `POST /api/bookmarks`
- `PUT /api/bookmarks/{id}`
- `DELETE /api/bookmarks/{id}`

## Exemple payload

```json
{
  "title": "JetBrains Blog",
  "url": "https://blog.jetbrains.com"
}
```

## Lancer les tests

```powershell
.\gradlew.bat test
```

## Lancer l'application

```powershell
.\gradlew.bat bootRun
```

