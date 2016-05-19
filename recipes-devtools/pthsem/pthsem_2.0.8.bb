SUMMARY = "GNU pth is a user mode multi threading library. pthsem is a fork, with support for semaphores added."
SECTION = "base"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=4c603c471bc48b83d1bb6fd35e9b742a"

SRC_URI = "https://www.auto.tuwien.ac.at/~mkoegler/pth/pthsem_${PV}.tar.gz"

inherit autotools gettext pkgconfig

SRC_URI[md5sum] = "9144b26dcc27e67498d63dd5456f934c"
SRC_URI[sha256sum] = "4024cafdd5d4bce2b1778a6be5491222c3f6e7ef1e43971264c451c0012c5c01"


SYSROOT_PREPROCESS_FUNCS += "pthsem_sysroot_preprocess"

pthsem_sysroot_preprocess () {
	install -d ${SYSROOT_DESTDIR}${bindir}/
	install -m 755 ${B}/pth-config ${SYSROOT_DESTDIR}${bindir}/
}
