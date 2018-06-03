<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    .content {
                    text-align: left;
                    display: flex;
                    flex-direction: column;
                    max-width: 600px;
                    }
                </style>
            </head>
            <body>
                <div class="content">
                    <xsl:apply-templates/>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="owners">
        <xsl:for-each select="owner">
            <ul>
                <li>
                    Id:
                    <xsl:value-of select="id"/>
                </li>
                <li>
                    <xsl:value-of select="name"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="patronymic"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="surname"/>
                </li>
                <li>
                    <xsl:value-of select="dateOfBirth"/>
                </li>
                <li>
                    <xsl:text>Машины: </xsl:text>
                    <xsl:apply-templates select="vehicles"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="typesOfVehicle">
        <table border="1" width="100%">
            <xsl:for-each select="typeOfVehicle">
                <tr>
                    <td>
                        <xsl:value-of select="id"/>
                    </td>
                    <td>
                        <xsl:value-of select="name"/>
                    </td>
                    <td>
                        <xsl:value-of select="minimum_weight"/>
                    </td>
                    <td>
                        <xsl:value-of select="maximum_weight"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
    <xsl:template match="vehicles">
        <xsl:for-each select="vehicle">
            <ul>
                <li>
                    Id:
                    <xsl:value-of select="id"/>
                </li>
                <li>
                    Название:
                    <xsl:value-of select="name"/>
                </li>
                <li>
                    Бренд:
                    <xsl:value-of select="brand"/>
                </li>
                <li>
                    Год выпуска:
                    <xsl:value-of select="yearOfIssue"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>