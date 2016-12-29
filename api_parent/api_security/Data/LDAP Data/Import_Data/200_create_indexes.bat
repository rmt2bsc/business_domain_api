call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "city" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "state" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "timeZoneId" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "zip" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "zipId" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "areaCode" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "c" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "countryId" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
call dsconfig -h localhost -p 4444 -D "cn=directory manager" -w drum7777 -X -n create-local-db-index --backend-name userRoot "--index-name" "provinceId" "--set" "index-type:approximate" "--set" "index-type:equality" "--set" "index-type:ordering" "--set" "index-type:presence" "--set" "index-type:substring" "--set" "index-entry-limit:40000"
