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

@sql[query]
###

@sql[query;limit;offset;bind]
###

@save[filename]
$result[^void::_has_no_constructor_[]]
###

@save[filename;options]
$result[^void::_has_no_constructor_[]]
###

@save[nameless;filename]
$result[^void::_has_no_constructor_[]]
###

@save[nameless;filename;options]
$result[^void::_has_no_constructor_[]]
###

@save[append;filename]
$result[^void::_has_no_constructor_[]]
###

@save[append;filename;options]
$result[^void::_has_no_constructor_[]]
###

@count[]
$result(1)
###

@menu[code]
$result[^void::_has_no_constructor_[]]
###

@menu[code;split]
$result[^void::_has_no_constructor_[]]
###

@append[data]
$result[^void::_has_no_constructor_[]]
###

@offset[offset]
$result[^void::_has_no_constructor_[]]
###

@offset[cur;offset]
$result[^void::_has_no_constructor_[]]
###

@offset[set;offset]
$result[^void::_has_no_constructor_[]]
###

@line[]
$result(1)
###

@sort[function]
$result[^void::_has_no_constructor_[]]
###

@sort[function;order]
$result[^void::_has_no_constructor_[]]
###

@join[table]
$result[^void::_has_no_constructor_[]]
###

@join[table;options]
$result[^void::_has_no_constructor_[]]
###

@flip[]
$result[^table::create[]]
###

@locate[column;seachword]
###

@locate[column;seachword;options]
###

@select[criteria]
$result[^table::create[]]
###

@select[criteria;options]
$result[^table::create[]]
###

@hash[key]
$result[^hash::create[]]
###

@hash[key;options]
$result[^hash::create[]]
###

@hash[key;column;options]
$result[^hash::create[]]
###

@columns[]
$result[^table::create[]]
###

@columns[column]
$result[^table:;create[]]
###