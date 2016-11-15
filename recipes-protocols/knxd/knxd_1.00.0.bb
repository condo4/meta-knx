SUMMARY = "KNXD extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRC_URI = "https://github.com/knxd/knxd/archive/master.tar.gz \
			file://use-pkgconfig-instead-of-pth-config.patch"

S = "${WORKDIR}/knxd-master"

SRC_URI[md5sum] = "032852611ea8271b4c8fa294def9c9f3"
SRC_URI[sha256sum] = "12be4b572c9bfb07856fbd95953ded42c78d0840ecb9629180df2a82acbb422c"

DEPENDS += "pthsem libusb1 ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','systemd', '', d)}"
RDEPENDS_${PN} = "pthsem libusb1 ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','libsystemd', '', d)}"


EXTRA_OECONF = " \
    --without-pth-test \
    --enable-eibnetip \
    --enable-eibnetiptunnel \
    --enable-eibnetipserver \
    --enable-usb \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','--enable-systemd', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','--with-systemd=${systemd_unitdir}/system/', '', d)} \
"

inherit autotools-brokensep gettext pkgconfig useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system knxd"
USERADD_PARAM_${PN} =  "--system --home /var/lib/knxd --no-create-home --gid knxd knxd"

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)};
    then
        install -d ${D}/${sysconfdir}
        install -m 644 ${S}/systemd/knxd.conf ${D}/${sysconfdir}
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${S}/systemd/knxd.service ${D}/${systemd_unitdir}/system
        install -m 644 ${S}/systemd/knxd.socket ${D}/${systemd_unitdir}/system
    else
        install -d ${D}/${sysconfdir}/init.d
        install -m 0655 ${S}/debian/knxd.init ${D}${sysconfdir}/init.d/knxd.init
    fi
}

PACKAGES =+ " ${PN}-exemples-dbg  ${PN}-exemples "

FILES_${PN} += " ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','${systemd_unitdir}', '', d)} "
FILES_${PN}-exemples += "/usr/share/knxd/examples"
FILES_${PN}-exemples-dbg += "/usr/share/knxd/examples/bin/.debug"
