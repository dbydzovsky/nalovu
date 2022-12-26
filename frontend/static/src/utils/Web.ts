

export function getBaseUrl() {
    return process.env.NODE_ENV === "development" ? "http://localhost:8080" : "";
}