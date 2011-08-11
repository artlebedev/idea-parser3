#!/bin/bash

# idea community builds are time consuming.
#
# Once you have built them once you can skip the build again by invoking this script like this
#
# SKIP_IDEA_BUILD=true ./package_linux.sh
#
# Every variable below (except for SOURCE_PATH_IDEA_BUILT) can be overridden in this fashion.
#

SOURCE_PATH_IDEA=${SOURCE_PATH_IDEA:-${HOME}/Projects/ideaIC-107.105/}
SOURCE_PATH_IDEA_BUILT=idea_community_not_built
SOURCE_PATH_PARSER_PLUGIN=${SOURCE_PATH_PARSER_PLUGIN:-`pwd`/..}

SKIP_IDEA_BUILD=${SKIP_IDEA_BUILD:-false}

RELEASE_TAG_IDEA=${RELEASE_TAG_IDEA:-idea/108.857}

PARSER_IDE_VERSION=${PARSER_IDE_VERSION:-1.0.0}

FOLDER_DIST=${FOLDER_DIST:-${SOURCE_PATH_PARSER_PLUGIN}/dist}

# linux | darwin | windows
TARGET_HOST=${TARGET_HOST:-linux}

function validate_idea_community_location() {

    if [ ! -d "${SOURCE_PATH_IDEA}" ]; then
        echo "Error: '${SOURCE_PATH_IDEA}' is not a folder"
        exit 1
    fi

    if [ ! -d "${SOURCE_PATH_IDEA}/.git" ]; then
        echo "Error: '${SOURCE_PATH_IDEA}' is not a idea checkout folder (is missing Git metadata)"
        exit 1
    fi

    pushd "${SOURCE_PATH_IDEA}" >/dev/null
    ACTUAL_RELEASE_TAG_IDEA=`git describe`
    if [ "${RELEASE_TAG_IDEA}" != "${ACTUAL_RELEASE_TAG_IDEA}" ]; then
        echo "Error: Intellij Idea Community source tree is at the wrong tag: ${ACTUAL_RELEASE_TAG_IDEA}"
        echo -e " Try this:"
        echo -e "\tpushd '${SOURCE_PATH_IDEA}' && git pull && git checkout ${RELEASE_TAG_IDEA} && popd"
        echo
        exit 1
    fi

    echo "GOOD: Intellij Idea Community source tree is at the proper release ('${RELEASE_TAG_IDEA}' vs '${ACTUAL_RELEASE_TAG_IDEA}')"
    popd >/dev/null
}

function build_idea_community() {
    echo
    echo
    echo "Cleanly building Idea Community"
    echo
    pushd ${SOURCE_PATH_IDEA} >/dev/null

    # the default build target in Intellij Community will do a clean,build
    ant
    popd
}

function build_idea_parser_plugin() {

    echo
    echo
    echo "Cleanly building Idea Parser plugin"
    echo
    pushd "${SOURCE_PATH_PARSER_PLUGIN}" >/dev/null

    # the default build target in Intellij Community will do a clean,build
    ant -Didea.community.build=${SOURCE_PATH_IDEA_BUILT} -f build-package.xml

    popd
}

function extract_idea_community_build() {
    IDEA_BUILD_VERSION=`cat "${SOURCE_PATH_IDEA}/build.txt"`

    SOURCE_PATH_IDEA_BUILT=${FOLDER_DIST}/idea-IC-${IDEA_BUILD_VERSION}-${TARGET_HOST}

    if [ -d "${SOURCE_PATH_IDEA_BUILT}" ]; then
        rm -rf "${SOURCE_PATH_IDEA_BUILT}"
    fi

    pushd "${FOLDER_DIST}" >/dev/null
    if [ "${TARGET_HOST}" == "linux" ]; then

        if [ -d "${FOLDER_DIST}/idea-IC-${IDEA_BUILD_VERSION}" ]; then
            rm -rf "${FOLDER_DIST}/idea-IC-${IDEA_BUILD_VERSION}"
        fi

        tar -xzvf ${SOURCE_PATH_IDEA}/out/artifacts/ideaIC-${IDEA_BUILD_VERSION}.tar.gz
        mv ${FOLDER_DIST}/idea-IC-${IDEA_BUILD_VERSION} ${FOLDER_DIST}/idea-IC-${IDEA_BUILD_VERSION}-linux
    elif [ "${TARGET_HOST}" == "darwin" ]; then

        if [ -d "${FOLDER_DIST}/Community Edition-IC-${IDEA_BUILD_VERSION}.app" ]; then
            rm -rf "${FOLDER_DIST}/Community Edition-IC-${IDEA_BUILD_VERSION}.app"
        fi

        unzip "${SOURCE_PATH_IDEA}/out/artifacts/ideaIC-${IDEA_BUILD_VERSION}.mac.zip"
        mv "${FOLDER_DIST}/Community Edition-IC-${IDEA_BUILD_VERSION}.app" "${SOURCE_PATH_IDEA_BUILT}"
    elif [ "${TARGET_HOST}" == "windows" ]; then
        mkdir ${SOURCE_PATH_IDEA_BUILT}
        pushd ${SOURCE_PATH_IDEA_BUILT} >/dev/null
        unzip ${SOURCE_PATH_IDEA}/out/artifacts/ideaIC-${IDEA_BUILD_VERSION}.win.zip
        popd >/dev/null
    fi

    popd >/dev/null
    echo ${SOURCE_PATH_IDEA_BUILT}
}

function assemble_distribution() {
    echo
    echo
    echo "Assembling distribution for arch $1"
    echo
    pushd "${SOURCE_PATH_PARSER_PLUGIN}" >/dev/null

    ant \
        -Dparser.ide.target.package=${SOURCE_PATH_PARSER_PLUGIN}/dist/parseride-${TARGET_HOST}_$1.zip \
        -Didea.community.build=${SOURCE_PATH_IDEA_BUILT} \
        -Dtarget.platform=${TARGET_HOST} \
        -Dparser.plugin=${SOURCE_PATH_PARSER_PLUGIN}/dist/idea-parser.jar \
        -f build-distribution.xml

    popd > /dev/null
}

# enable fail on command errors
set -eo pipefail

validate_idea_community_location

if [ "${SKIP_IDEA_BUILD}" != "true" ]; then
    build_idea_community
fi

extract_idea_community_build
build_idea_parser_plugin

assemble_distribution 386
if [ "${TARGET_HOST}" != "windows" ]; then
    assemble_distribution amd64
fi