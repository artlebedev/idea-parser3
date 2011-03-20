@CLASS
date

@create[days_after_erosn_or_date]
###

@create[year;month]
###

@create[year;month;day]
###

@create[year;month;day;hour;minute;second]
###

@now[]
###

@now[days_offset]
###

@unix-timestamp[unix_datetime]
###

@roll[year;offset]
$result[^void::_has_no_constructor[]]
###

@roll[month;offset]
$result[^void::_has_no_constructor[]]
###

@roll[day;offset]
$result[^void::_has_no_constructor[]]
###

@roll[TZ;new_timezone]
$result[^void::_has_no_constructor[]]
###

@sql-string[]
$result[]
###

@unix-timestamp[]
$result[]
###

@last-day[]
$result(1)
###

@gmt-string[]
$result[]
###

@static:calendar[rus;year;month;day]
$result[^table::create[]]
###

@static:calendar[eng;year;month;day]
$result[^table::create[]]
###

@static:calendar[rus;year;month]
$result[^table::create[]]
###

@static:calendar[eng;year;month]
$result[^table::create[]]
###

@static:last-day[year;month]
$result(1)
###