# URL Shortener Service
This is a URL shortener service built with SpringBoot and MongoDB.

### Running the Application
1. Clone this repository to your local machine:
```
git clone https://github.com/chatgut/URL-Shortener-Service.git
```
2. Navigate to the project directory:
```
cd url-shortener-service
```
3. Start the application using Docker Compose:
```
docker-compose up
```
4. You are now done! Once Docker Compose has started the containers, you can access the application at `http://localhost:8080`.
 
## Configuration

### MongoDB URI
The application is configured to connect to MongoDB using the following URI:
```
mongodb://mongo:27017/urlshortener
```
If you need to customize the MongoDB connection settings, you can update the `SPRING_DATA_MONGODB_URI` environment variable in the `docker-compose.yml` file.

# Usage Examples

## [POST] - Shortening a URL
To shorten a URL, send a POST request to the `/api/urls` endpoint with the original URL in the request body.

JSON Body:
```JSON
{
    "originalUrl": "http://example.com"
}
```
Output:
```JSON
{
    "id": "664ba4785b64a14ee2d09a26",
    "originalUrl": "http://example.com",
    "shortUrl": "2edf5e51",
    "createdAt": "2024-05-20T19:28:56.447953337"
}
```
## [GET] - Show all generated URL(s) and content (just like Output above)
```
http://localhost:8080/api/urls
```
## [GET] - Redirect to original URL
To redirect to the original URL, send a GET request to the `/api/urls/goto/{shortUrl}`. Replace `{shortUrl}` with the "shortUrl", example `2edf5e51`.
## [DELETE] - Remove URL from DB
To remove URL from DB, send DELETE request to `/api/urls/{id}`. Replace `{id}` with the related "id", example `664ba4785b64a14ee2d09a26`.
