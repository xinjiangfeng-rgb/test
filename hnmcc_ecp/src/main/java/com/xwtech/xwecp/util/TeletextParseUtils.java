package com.xwtech.xwecp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TeletextParseUtils
{
    /**
     * 将以~号分隔字段、以;号分隔记录的报文解析成Map的List 此报文的第一条记录为字段名，第二天记录开始为字段值，生成的Map以字段名做键，以字段值做值 例如：
     * 报文格式：基站号~位置小区号~;39F0~500E~;39F1~501E~; 返回的List中含两个Map，键值对分别为："基站号"--"39F0","位置小区号" -- "500E"和"基站号"--"39F1"
     * "位置小区号" -- "501E"
     * 
     * @param content
     * @return
     */
    public static List<Map<String, String>> parseXTABLE(String content)
    {
        List<Map<String, String>> respList = null;
        String[] data = content.split(";");
        if (data.length > 1)
        {
            respList = new ArrayList<Map<String, String>>();
            String[] title = data[0].toString().split("~");
            
            // 将数据分配到对应的字段
            for (int i = 1; i < data.length; i++)
            {
                String[] usefulData = data[i].toString().split("~");
                
                int usefulDataNum = usefulData.length;
                Map<String, String> record = new TreeMap<String, String>();
                for (int j = 0; j < title.length; j++)
                {
                    if (j < usefulDataNum)
                    {
                        record.put((String)title[j], (String)usefulData[j]);
                    }
                    else
                    {
                        record.put((String)title[j], "");
                    }
                }
                respList.add(record);
            }
        }
        
        return respList;
    }
    
    // 金额～流水类型～被充值手机号码～充值时间～撤销标志～撤销时间
    private static String[] paramNames = new String[] {"金额", "流水类型", "被充值手机号码", "充值时间", "撤销标志", "撤销时间"};
    
    /**
     * 解析华为的返回信息
     * 
     * @param seqContent
     * @return
     */
    public static List<Map<String, String>> parseXTABLE_HW(String seqContent)
    {
        List<Map<String, String>> respList = null;
        String[] data = seqContent.split(";");
        if (data.length > 0)
        {
            respList = new ArrayList<Map<String, String>>();
            
            // 解析
            for (int i = 0; i < data.length; i++)
            {
                String[] usefulData = data[i].toString().split("~");
                int usefulDataNum = usefulData.length;
                
                // 如果字段参数大于1
                if (usefulDataNum > 1)
                {
                    Map<String, String> record = new TreeMap<String, String>();
                    for (int j = 0; j < paramNames.length; j++)
                    {
                        if (record.containsKey(paramNames[j]))
                        {
                            continue;
                        }
                        if (j < usefulDataNum)
                        {
                            record.put((String)paramNames[j], (String)usefulData[j]);
                        }
                        else
                        {
                            record.put((String)paramNames[j], "");
                        }
                    }
                    respList.add(record);
                }
                // 不大于
                else
                {
                    respList = null;
                    break;
                }
            }
        }
        return respList;
    }
    
    public static String parseHead(String head)
    {
        String f_row = head.substring(head.indexOf("FFFFFFFF") + 8);
        return f_row.split("\\|")[0];
    }
    
    public static void main(String[] a)
    {
        
        String s = "MHF2015020515459115940032XZT01 HNYD00201502091714020081  1 888 8  0000调用成功                                  FFFFFFFF2015-01-22 14:16:13 驻马店  呼转主叫      18703659581     1分4秒  省际长途        4G飞享套餐-58元套餐             0.00    1.80|呼叫转移长途费:1.80元,国内漫游主叫通话费(不含港澳台):0.00元";
        
        System.out.println(parseHead(s));
        
        // String t =
        // "20100601100240103650~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200008224550~15189103793~15~0~20100601100241~空中充值帐户转帐~0~0~0~201006~0~0~;20100601100252103653~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200003360754~13862432494~15~0~20100601100253~空中充值帐户转帐~0~0~0~201006~0~0~;20100601100606103654~400000~1842200003120738~15951285799~1842200003120739~1~0~1845200006220622~13912105061~15~0~20100601100608~空中充值帐户转帐~0~0~0~201006~0~0~;20100601100638103656~540000~1842200003120738~15951285799~1842200003120739~1~0~1845200004125717~13912108576~15~0~20100601100639~空中充值帐户转帐~0~0~0~201006~0~0~;20100601100248103652~400000~1842200003120738~15951285799~1842200003120739~1~0~1843200004400952~13626266505~15~0~20100601100249~空中充值帐户转帐~0~0~0~201006~0~0~;20100601100244103651~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200005850597~13405584847~15~0~20100601100245~空中充值帐户转帐~0~0~0~201006~0~0~;20100601094016103642~400000~1842200003120738~15951285799~1842200003120739~1~0~1845200008705131~15052998108~15~0~20100601094018~空中充值帐户转帐~0~0~0~201006~0~0~;20100601094004103640~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200003584724~15952930990~15~0~20100601094005~空中充值帐户转帐~0~0~0~201006~0~0~;20100601094000103639~221000~1842200003120738~15951285799~1842200003120739~1~0~1845200003584716~13775392121~15~0~20100601094001~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093952103637~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200008083748~13775528787~15~0~20100601093953~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093948103636~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200005087312~13913407101~15~0~20100601093949~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170357103730~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200004284209~13861375171~15~0~20100601170358~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170427103737~854000~1842200003120738~15951285799~1842200003120739~1~0~1845200004247087~13952997848~15~0~20100601170428~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170854103749~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200007416583~13952981133~15~0~20100601170855~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170947103765~500000~1842200003120738~15951285799~1842200003120739~1~0~1844200007204316~13815184152~15~0~20100601170948~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171114103770~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200005622589~13952857490~15~0~20100601171115~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170439103740~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200005262727~13451969077~15~0~20100601170441~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170939103763~300000~1842200003120738~15951285799~1842200003120739~1~0~1844200004756102~13645288454~15~0~20100601170940~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171745103790~500000~1842200003120738~15951285799~1842200003120739~1~0~1845200008748912~13775382026~15~0~20100601171746~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170423103736~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200004460494~13775380135~15~0~20100601170424~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170516103745~600000~1842200003120738~15951285799~1842200003120739~1~0~1845200003790552~13952967309~15~0~20100601170517~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170846103747~400000~1842200003120738~15951285799~1842200003120739~1~0~1844200005218183~13952906595~15~0~20100601170848~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170920103757~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200008280571~13775308117~15~0~20100601170921~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170932103761~300000~1842200003120738~15951285799~1842200003120739~1~0~1842200004886995~15850442322~15~0~20100601170933~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171421103781~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200005881134~15850451153~15~0~20100601171422~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171054103768~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200004260585~13815178802~15~0~20100601171055~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171307103776~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200006281850~15951278186~15~0~20100601171309~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171445103783~400000~1842200003120738~15951285799~1842200003120739~1~0~1842200003817689~13913430027~15~0~20100601171446~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170348103728~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200008826537~15952992550~15~0~20100601170350~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170447103742~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200009648023~13645293957~15~0~20100601170448~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170509103743~310000~1842200003120738~15951285799~1842200003120739~1~0~1845200003780693~15952968890~15~0~20100601170510~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170844103746~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200005722697~15850451443~15~0~20100601170845~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170858103750~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200007448750~13705296955~15~0~20100601170900~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170906103753~400000~1842200003120738~15951285799~1842200003120739~1~0~1844200004756098~13861362522~15~0~20100601170907~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171007103766~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200006127115~15052985530~15~0~20100601171008~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171718103788~550000~1842200003120738~15951285799~1842200003120739~1~0~1845200005087312~13913407101~15~0~20100601171720~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170411103733~260000~1842200003120738~15951285799~1842200003120739~1~0~1845200004296546~13952967110~15~0~20100601170412~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170850103748~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200008909812~13815185691~15~0~20100601170851~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170924103759~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200007114595~13952980940~15~0~20100601170926~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171206103773~300000~1842200003120738~15951285799~1842200003120739~1~0~1842200004075285~15951280884~15~0~20100601171208~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171338103778~1100000~1842200003120738~15951285799~1842200003120739~1~0~1842200003153364~13852919223~15~0~20100601171340~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171531103785~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200006041378~13952861211~15~0~20100601171532~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170353103729~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200009623174~15052995067~15~0~20100601170354~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170908103754~500000~1842200003120738~15951285799~1842200003120739~1~0~1842200005574066~15862974234~15~0~20100601170909~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170922103758~1000000~1842200003120738~15951285799~1842200003120739~1~0~1842200005479677~15896352086~15~0~20100601170923~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170935103762~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200005824588~13656139321~15~0~20100601170936~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170345103727~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200004877610~13812366871~15~0~20100601170346~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170400103731~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200003638166~13952939720~15~0~20100601170402~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170443103741~500000~1842200003120738~15951285799~1842200003120739~1~0~1845200004719036~13775526157~15~0~20100601170444~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171321103777~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200003150878~13952858828~15~0~20100601171322~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171351103779~250000~1842200003120738~15951285799~1842200003120739~1~0~1842200004075285~15951280884~15~0~20100601171352~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171457103784~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200003812431~13775326963~15~0~20100601171458~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170902103752~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200003785645~13605295828~15~0~20100601170903~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170913103755~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200004746365~13615280130~15~0~20100601170914~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170928103760~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200008085034~13775312866~15~0~20100601170930~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170943103764~300000~1842200003120738~15951285799~1842200003120739~1~0~1844200003916746~13952879475~15~0~20100601170944~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171253103775~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200003259709~13852942929~15~0~20100601171254~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171405103780~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200006594360~13852997137~15~0~20100601171407~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171658103787~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200005381340~13705292575~15~0~20100601171659~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170404103732~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200003556165~13952932166~15~0~20100601170405~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170415103734~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200008321062~13505299744~15~0~20100601170417~空中充值帐户转帐~0~0~0~201006~0~0~;20100601170432103738~460000~1842200003120738~15951285799~1842200003120739~1~0~1845200005714834~15952978608~15~0~20100601170434~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171139103771~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200006433306~13815180865~15~0~20100601171141~空中充值帐户转帐~0~0~0~201006~0~0~;20100601171235103774~500000~1842200003120738~15951285799~1842200003120739~1~0~1842200008233053~13656131001~15~0~20100601171236~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093832103620~400000~1842200003120738~15951285799~1842200003120739~1~0~1843200006370358~15896398169~15~0~20100601093833~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093925103631~250000~1842200003120738~15951285799~1842200003120739~1~0~1845200004282606~13952997100~15~0~20100601093926~空中充值帐户转帐~0~0~0~201006~0~0~;20100601094008103641~230000~1842200003120738~15951285799~1842200003120739~1~0~1845200004672566~13952967445~15~0~20100601094009~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093806103615~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200006912364~13912116411~15~0~20100601093807~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093900103626~100000~1842200003120738~15951285799~1842200003120739~1~0~1844200004922387~13952890129~15~0~20100601093902~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093809103616~300000~1842200003120738~15951285799~1842200003120739~1~0~1842200008000422~13655285956~15~0~20100601093811~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093828103619~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200006042671~15052915826~15~0~20100601093829~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093921103630~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200005714834~15952978608~15~0~20100601093922~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093844103623~550000~1842200003120738~15951285799~1842200003120739~1~0~1844200003437901~13815188730~15~0~20100601093845~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093939103635~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200004030490~13775399990~15~0~20100601093941~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093956103638~800000~1842200003120738~15951285799~1842200003120739~1~0~1845200003584721~13921557299~15~0~20100601093957~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093816103618~500000~1842200003120738~15951285799~1842200003120739~1~0~1842200005966214~15052945057~15~0~20100601093818~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093840103622~700000~1842200003120738~15951285799~1842200003120739~1~0~1842200004680335~15951409775~15~0~20100601093841~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093905103627~280000~1842200003120738~15951285799~1842200003120739~1~0~1845200004718998~13914591524~15~0~20100601093906~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093928103632~300000~1842200003120738~15951285799~1842200003120739~1~0~1845200008837290~15996820218~15~0~20100601093930~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093852103624~300000~1842200003120738~15951285799~1842200003120739~1~0~1844200007097368~13952907979~15~0~20100601093854~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093912103629~250000~1842200003120738~15951285799~1842200003120739~1~0~1845200004986895~13852976299~15~0~20100601093913~空中充值帐户转帐~0~0~0~201006~0~0~;20100601092910103612~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200003259709~13852942929~15~0~20100601092911~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093856103625~200000~1842200003120738~15951285799~1842200003120739~1~0~1844200005571804~13861362669~15~0~20100601093858~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093932103633~730000~1842200003120738~15951285799~1842200003120739~1~0~1845200003590365~13775522119~15~0~20100601093934~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093836103621~200000~1842200003120738~15951285799~1842200003120739~1~0~1842200004279690~15952869386~15~0~20100601093837~空中充值帐户转帐~0~0~0~201006~0~0~;20100601093908103628~200000~1842200003120738~15951285799~1842200003120739~1~0~1845200004911728~13913409374~15~0~20100601093910~空中充值帐户转帐~0~0~0~201006~0~0~";
        //
        // for (Map<String, String> map : parseXTABLE_HW(t))
        // {
        // Set<String> keySet = map.keySet();
        // System.out.println("*******************************************");
        // for (String key : keySet)
        // {
        // System.out.println("KEY: " + key + " VALUE: " + map.get(key));
        // }
        // System.out.println("*******************************************");
        // }
    }
    
}