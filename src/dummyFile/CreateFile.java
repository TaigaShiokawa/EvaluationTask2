package dummyFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CreateFile {

	protected static boolean createDummyFile(int byteSize) {
		File file = new File("./DummyFile");
		/*ファイルがある場合のみ削除。
		 * ファイルの存在を確認する理由は、存在しないファイルを削除しようとした際、ファイルシステに誤作動が起き何かしらのエラーが起こる可能性があると考えたため。
		 * */
		if(file.exists()) {
			file.delete();
		}
		try (RandomAccessFile rFile = new RandomAccessFile(file.getPath(), "rw")) {
			rFile.setLength(byteSize);
			//try-with-resources構文内で自動的にクローズされるためrFile.close()は不要だと思います。
//			rFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
