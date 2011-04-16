@CLASS
xnode

@_has_no_constructor_[]
###

#:dynamic
@insertBefore[newChild;refChild]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@replaceChild[newChild;refChild]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@removeChild[oldChild]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@appendChild[oldChild]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@hasChildNodes[]
$result[^bool::_has_no_constructor_[]]
###

#:dynamic
@cloneNode[deep]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@getAttribute[name]
$result[]
###

#:dynamic
@setAttribute[name;value]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@removeAttribute[name;value]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@getElementsByTagName[name]
$result[^hash::create[]]
###

#:dynamic
@getAttributeNS[namespaceURI;localName]
$result[]
###

#:dynamic
@setAttributeNS[namespaceURI;qualifiedName;value]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@removeAttributeNS[namespaceURI;localName]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@getAttributeNodeNS[namespaceURI;localName]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@setAttributeNodeNS[newAttr]
$result[^xnode::_has_no_constructor_[]]
###

#:dynamic
@getElementsByTagNameNS[namespaceURI;localName]
$result[^hash::create[]]
###

#:dynamic
@hasAttribute[name]
$result[^bool::_has_no_constructor_[]]
###

#:dynamic
@hasAttributeNS[namespaceURI;localName]
$result[^bool::_has_no_constructor_[]]
###

#:dynamic
@hasAttributes[]
$result[^bool::_has_no_constructor_[]]
###