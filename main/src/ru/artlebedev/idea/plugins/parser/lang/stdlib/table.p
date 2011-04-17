@CLASS
table

@create[data]
###

@create[nameless;data]
###

@create[data;options]
###

@load[filename]
###

@load[filename;options]
###

@load[nameless;filename]
###

@load[nameless;filename;options]
###

#:constructor
@sql[query]
###

#:constructor
@sql[query;limit;offset;bind]
###

#:dynamic
@save[filename]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@save[filename;options]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@save[nameless;filename]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@save[nameless;filename;options]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@save[append;filename]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@save[append;filename;options]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@count[]
$result(1)
###

#:dynamic
@menu[code]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@menu[code;split]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@append[data]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@offset[offset]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@offset[cur;offset]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@offset[set;offset]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@line[]
$result(1)
###

#:dynamic
@sort[function]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@sort[function;order]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@join[table]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@join[table;options]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@flip[]
$result[^table::create[]]
###

#:dynamic
@locate[column;seachword]
###

#:dynamic
@locate[column;seachword;options]
###

#:dynamic
@select[criteria]
$result[^table::create[]]
###

#:dynamic
@select[criteria;options]
$result[^table::create[]]
###

#:dynamic
@hash[key]
$result[^hash::create[]]
###

#:dynamic
@hash[key;options]
$result[^hash::create[]]
###

#:dynamic
@hash[key;column;options]
$result[^hash::create[]]
###

#:dynamic
@columns[]
$result[^table::create[]]
###

#:dynamic
@columns[column]
$result[^table:;create[]]
###