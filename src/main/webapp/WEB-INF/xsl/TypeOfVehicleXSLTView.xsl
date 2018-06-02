<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
            <body>
                <div align="center">
                    <xsl:apply-templates />
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="typesOfVehicle">
        <table border="1" width="100%">
            <xsl:for-each select="typeOfVehicle">
                <tr>
                    <td>
                        <xsl:value-of select="id" />
                    </td>
                    <td>
                        <xsl:value-of select="name" />
                    </td>
                    <td>
                        <xsl:value-of select="minimum_weight" />
                    </td>
                    <td>
                        <xsl:value-of select="maximum_weight" />
                    </td>

                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>