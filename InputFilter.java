import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 敏感词屏蔽器
 * 字符串中最长敏感词替换为***
 */
public class InputFilter {
	
	private static InputFilter filter = new InputFilter();
	
	//敏感词黑名单
	private Set<String> filterBase = new HashSet<String>();
	private final static int maxLength = Character.MAX_VALUE;
	
	//敏感词长度数组
	@SuppressWarnings("unchecked")
	private HashSet<Integer>[] filterLengths = new HashSet[maxLength]; 
	
	private InputFilter(){
		init(this.getClass().getClassLoader().getResource("forbidden.txt").getFile());
	}
	
	/**
	 * 
	 * 敏感词过滤主方法
	 * 
	 * 1.从输入字符串依次变量每个字符
	 * 2.取该字符对应的int值 作为数组下标从敏感词数组取对象 如果没有 说明该字符不是敏感字符开头字符 继续处理下一字符
	 * 3.若改字符对应的Int值 作为敏感词数组小标读取到对象 说明该字符是特定敏感词的开头字符
	 * 4.从敏感词数组按该字符int值取出对应的存储了以该字符开头的所有敏感词长度的hashSet 分别对各个长度进行处理
	 * 5.以当前字符的位置加上上一步读取的长度 从输入字符串截取子字符串 然后去敏感词hashSet里找 如果找到 说明是敏感词
	 * 6.分别对步骤4得到的不同长度进行处理 得到一个最大长度 该最大长度即为匹配到的最长敏感词
	 * 7.从当前字符开始 截取最长敏感词长度的子字符串 将其替换为***
	 * 8.继续从当前字符+最长敏感词长度位置处理输入字符串
	 *
	 * @param input
	 * @return
	 */
	public String filter(String input){
		StringBuilder res = new StringBuilder();
		int start = 0;
		
		for(int i = 0; i < input.length();){
			int index = input.charAt(i);
			if(null == filterLengths[index]){
				i++;
				continue;
			}else{
				int max = 0;
				for(Object obj : filterLengths[index].toArray()){
					 int len = ((Integer)obj).intValue();
					if(i + len < input.length()){
						String tmp = input.substring(i, i + len);
						if(filterBase.contains(tmp)){
							max = len > max ? len : max;
						}
					}
				}
				
				if(max > 0){
					res.append(input.substring(start, i)).append("***");
					i += max;
					start = i;
				}else{
					i++;
				}
			}
		}
		
		if(start < input.length()){
			res.append(input.substring(start));
		}
		
		return res.toString();
	}
	
	/**
	 * 
	 * 1.读取包含所有敏感词的黑名单文件
	 * 2.依次读取各个敏感词
	 * 3.将敏感词保存到HashSet里
	 * 4.读取敏感词的第一个字符 将该字符转换为int 将int值作为敏感词长度数组的下标
	 * 5.从敏感词长度数组中按下标取一个元素 该元素为HashSet 用来保存所有以相同字符开头的敏感词的长度
	 *
	 * @param filterFile
	 */
	private void init(String filterFile){
		FileInputStream in = null;
		InputStreamReader reader = null;
		BufferedReader bReader = null;
		try {
			in = new FileInputStream(filterFile);
			reader = new InputStreamReader(in);
			bReader = new BufferedReader(reader);
			
			String line = bReader.readLine();
			while(null != line){
				line = line.trim();
				//Initialize fileterBase
				this.filterBase.add(line);
				
				//Initialize filterLengths
				int c = line.charAt(0);
				if(null == filterLengths[c]){
					HashSet<Integer> tmp = new HashSet<Integer>();
					tmp.add(line.length());
					filterLengths[c] = tmp;
				}else{
					filterLengths[c].add(line.length());
				}
				line = bReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != bReader) bReader.close();
				if(null != reader) reader.close();
				if(null != in) in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(InputFilter.filter.filter("我是你大爷哈哈哈哈!"));
	}
}
