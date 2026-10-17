
#!/usr/bin/env bash

export SUPABASE_DB_URL='jdbc:postgresql://aws-1-us-west-2.pooler.supabase.com:5432/postgres?sslmode=require'
export SUPABASE_DB_USERNAME='postgres.zgpclhvblnlfjtpzjbuu'

export SUPABASE_DB_PASSWORD='VnO2v1OlxBH91qIt'

cd demo

./mvnw clean
./mvnw compile
./mvnw spring-boot:run
