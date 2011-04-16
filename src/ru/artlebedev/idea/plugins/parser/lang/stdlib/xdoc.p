@CLASS
xdoc

@create[xmlcode]
###

@create[basepath;xmlcode]
###

@create[tagname]
###

@create[basepath;tagname]
###

@create[file]
###

@load[filename]
###

#:dynamic
@createElement[tagName]
$result[^xnode::create[]]
###

#:dynamic
@createDocumentFragment[]
$result[^xnode::create[]]
###

#:dynamic
@createTextNode[data]
$result[^xnode::create[]]
###

#:dynamic
@createComment[data]
$result[^xnode::create[]]
###

#:dynamic
@createCDATASection[data]
$result[^xnode::create[]]
###

#:dynamic
@createProcessingInstruction[target;data]
$result[^xnode::create[]]
###

#:dynamic
@createAttribute[name]
$result[^xnode::create[]]
###

#:dynamic
@createEntityReference[name]
$result[^xnode::create[]]
###

#:dynamic
@getElementsByTagName[tagname]
$result[^hash::create[]]
###

#:dynamic
@importNode[importedNode;deep]
$result[^xnode::create[]]
###

#:dynamic
@createElementNS[namespaceURI;qualifiedName]
$result[^xnode::create[]]
###

#:dynamic
@createAttributeNS[namespaceURI;qualifiedName]
$result[^xnode::create[]]
###

#:dynamic
@getElementsByTagNameNS[namespaceURI;localName]
$result[^hash::create[]]
###

#:dynamic
@getElementById[elementId]
$result[^xnode::create[]]
###