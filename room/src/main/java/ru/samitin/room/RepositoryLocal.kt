package ru.samitin.room



interface RepositoryLocal <T> {
    suspend fun getData(): List< T>
    suspend fun saveToDB(data :T)
}