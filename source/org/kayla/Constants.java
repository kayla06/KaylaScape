/**
 * Copyright (c) 2015 Kyle Friz & Kayla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kayla;

import java.math.BigInteger;

/**
 * @author Kyle Friz
 * @author Kayla
 * @date Apr 9, 2015
 */
public class Constants {

	/**
	 * The Framework Name
	 */
	public static String FRAME_NAME = new String("KaylaScape");

	/**
	 * The Framework Version
	 */
	public static double FRAME_VERSION = new Double(1.0);

	/**
	 * The Framework Major Revision
	 */
	public static int FRAME_MAJOR = new Integer(76);

	/**
	 * The Framework Minor Revision
	 */
	public static int FRAME_MINOR = new Integer(1);

	/**
	 * The Cache Repository
	 */
	public static String CACHE_REPOSITORY = new String(
			System.getProperty("user.home") + "/Desktop/cache76/");


	/**
	 * The OnDemand Session Token
	 */
	public static String ONDEMAND_TOKEN = new String(
			"MpanIDx68ZShS/0wQc60lSvsuExhgYKEW");

	/**
	 * The Login Session Token
	 */
	public static String LOGIN_TOKEN = new String(
			"wwGlrZHF5gKN6D3mDdihco3oPeYN2KFybL9hUUFqOvk");

	/**
	 * The OnDemand Modulus RSA Key
	 */
	public static final BigInteger ONDEMAND_MODULUS = new BigInteger(
			"28878780788482598001642151942872822656447499206844235519153679359732277113310148479256917675834659964540266897204349506133897016325125553232761880223508365176463442317297944453587975834760309749799606139024342542436879154997555941419244203141500834556555071019951878485335378872917499107806023219513959922013456802305747155817282800436393202518972745310390008101874791569082508103110422791225127764099524300184402728134868370030395865195737606418022466437623942969534818338613865890114042146696815578588708162399858630601742556171590882374385666599899913023116349947395445184399859019574570125006659222741193776966437");

	/**
	 * The OnDemand Exponent RSA Key
	 */
	public static final BigInteger ONDEMAND_EXPONENT = new BigInteger(
			"10378594136306676087289280936119803958486167337821418117744581075724771088847428584340726338082819569782213196054961989684783808009302869452236760378172521247558531164064856275315586536400957864061982138218721331492375524324540871389405354480549447736554031886307377419856022988446615751196351143464185086945922618457939062368957264402781831041896488644906718725362083331462847462013765550620046678239134825954397202230811084921647412551980338050640385535087996994616684997750858249989646786856503252046803963418798978845594213883642556735623055127217792925975325425406857011628944456701115934485864789987650820019853");

	/**
	 * The Login Modulus RSA Key
	 */
	public static final BigInteger LOGIN_MODULUS = new BigInteger(
			"19012979679322751090497765534975417655929026410218510349530809374402362386904945611648917280260447297854993480796197679141793605816636437881403488432028679811535479071115526190776018539176396723209774866604217545482153134954622194342703772088941390195618242429735764401616074079857017544130815524275028212342385827138163840324748222938298718323420786581358312796598484759891694084973738022140687398891222340879741209792980896036387784379548876018068940879595558299131617023770571741204390526466942495798136973402713786648876104840620513332446279212480080045763952466309046498455877980777131551530035681061652824964113");

	/**
	 * The Login Exponent RSA Key
	 */
	public static final BigInteger LOGIN_EXPONENT = new BigInteger(
			"18752170232266719333927316914868563505255663352056152585149952795254892655519200977245872028952725825697713789335865626811835376852400110956835965718135859219342208160257036207323806190934646556827951047920463458883919226348427138836438750938935159977789217604914175799802551175271951722195517857974720746896701337871146497845635690990866380993863735083193662623824923789250473633668363886392805683296695402638408408717731361332747520625166123588330042418344572185154644325441079328795365394781614827520672449339959322373491664199924352325986969795631463240116724646228403709766373461408131433349158340913965506108825");

	/**
	 * The 838 Packet Sizes
	 */
	public static final int[] PACKET_SIZES = new int[121];

	/**
	 * Loads the 838 Packet Sizes
	 */
	static {
		PACKET_SIZES[0] = -1;
		PACKET_SIZES[1] = 7;
		PACKET_SIZES[2] = 1;
		PACKET_SIZES[3] = 8;
		PACKET_SIZES[4] = 8;
		PACKET_SIZES[5] = 3;
		PACKET_SIZES[6] = -1;
		PACKET_SIZES[7] = 4;
		PACKET_SIZES[8] = 0;
		PACKET_SIZES[9] = -1;
		PACKET_SIZES[10] = -1;
		PACKET_SIZES[11] = -1;
		PACKET_SIZES[12] = 16;
		PACKET_SIZES[13] = 11;
		PACKET_SIZES[14] = 3;
		PACKET_SIZES[15] = -2;
		PACKET_SIZES[16] = 3;
		PACKET_SIZES[17] = -1;
		PACKET_SIZES[18] = 3;
		PACKET_SIZES[19] = 8;
		PACKET_SIZES[20] = 0;
		PACKET_SIZES[21] = 15;
		PACKET_SIZES[22] = -1;
		PACKET_SIZES[23] = 6;
		PACKET_SIZES[24] = -1;
		PACKET_SIZES[25] = 7;
		PACKET_SIZES[26] = 7;
		PACKET_SIZES[27] = -1;
		PACKET_SIZES[28] = 8;
		PACKET_SIZES[29] = -1;
		PACKET_SIZES[30] = 2;
		PACKET_SIZES[31] = -1;
		PACKET_SIZES[32] = 0;
		PACKET_SIZES[33] = 8;
		PACKET_SIZES[34] = 3;
		PACKET_SIZES[35] = -2;
		PACKET_SIZES[36] = 18;
		PACKET_SIZES[37] = 11;
		PACKET_SIZES[38] = 9;
		PACKET_SIZES[39] = 1;
		PACKET_SIZES[40] = 9;
		PACKET_SIZES[41] = 9;
		PACKET_SIZES[42] = 1;
		PACKET_SIZES[43] = 9;
		PACKET_SIZES[44] = 15;
		PACKET_SIZES[45] = 3;
		PACKET_SIZES[46] = 3;
		PACKET_SIZES[47] = -1;
		PACKET_SIZES[48] = 5;
		PACKET_SIZES[49] = 3;
		PACKET_SIZES[50] = 3;
		PACKET_SIZES[51] = 4;
		PACKET_SIZES[52] = -1;
		PACKET_SIZES[53] = 9;
		PACKET_SIZES[54] = -1;
		PACKET_SIZES[55] = 8;
		PACKET_SIZES[56] = 18;
		PACKET_SIZES[57] = -2;
		PACKET_SIZES[58] = 4;
		PACKET_SIZES[59] = 4;
		PACKET_SIZES[60] = 4;
		PACKET_SIZES[61] = 3;
		PACKET_SIZES[62] = 12;
		PACKET_SIZES[63] = 8;
		PACKET_SIZES[64] = -1;
		PACKET_SIZES[65] = -1;
		PACKET_SIZES[66] = -1;
		PACKET_SIZES[67] = 6;
		PACKET_SIZES[68] = 2;
		PACKET_SIZES[69] = -1;
		PACKET_SIZES[70] = 3;
		PACKET_SIZES[71] = 7;
		PACKET_SIZES[72] = 0;
		PACKET_SIZES[73] = 4;
		PACKET_SIZES[74] = 1;
		PACKET_SIZES[75] = -1;
		PACKET_SIZES[76] = 4;
		PACKET_SIZES[77] = -1;
		PACKET_SIZES[78] = 7;
		PACKET_SIZES[79] = -1;
		PACKET_SIZES[80] = -2;
		PACKET_SIZES[81] = 16;
		PACKET_SIZES[82] = 9;
		PACKET_SIZES[83] = -2;
		PACKET_SIZES[84] = -1;
		PACKET_SIZES[85] = -1;
		PACKET_SIZES[86] = 0;
		PACKET_SIZES[87] = 8;
		PACKET_SIZES[88] = -2;
		PACKET_SIZES[89] = -1;
		PACKET_SIZES[90] = -2;
		PACKET_SIZES[91] = 3;
		PACKET_SIZES[92] = -2;
		PACKET_SIZES[93] = -1;
		PACKET_SIZES[94] = -1;
		PACKET_SIZES[95] = 3;
		PACKET_SIZES[96] = 1;
		PACKET_SIZES[97] = 9;
		PACKET_SIZES[98] = -2;
		PACKET_SIZES[99] = 7;
		PACKET_SIZES[100] = 17;
		PACKET_SIZES[101] = 4;
		PACKET_SIZES[102] = 3;
		PACKET_SIZES[103] = 0;
		PACKET_SIZES[104] = -1;
		PACKET_SIZES[105] = 4;
		PACKET_SIZES[106] = 2;
		PACKET_SIZES[107] = 1;
		PACKET_SIZES[108] = 3;
		PACKET_SIZES[109] = 7;
		PACKET_SIZES[110] = -2;
		PACKET_SIZES[111] = 3;
		PACKET_SIZES[112] = 4;
		PACKET_SIZES[113] = -1;
		PACKET_SIZES[114] = -2;
		PACKET_SIZES[115] = 8;
		PACKET_SIZES[116] = 5;
		PACKET_SIZES[117] = 6;
		PACKET_SIZES[118] = 3;
		PACKET_SIZES[119] = 8;
		PACKET_SIZES[120] = 9;
	}
}
