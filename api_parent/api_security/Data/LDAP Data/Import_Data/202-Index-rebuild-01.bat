call stop-ds
call rebuild-index.bat "--baseDN" "dc=rmt2,dc=net" "--index" "areaCode" "--index" "c" "--index" "city" "--index" "countryId" "--index" "provinceId" "--index" "state" "--index" "timeZoneId" "--index" "zip" "--index" "zipId"
call start-ds