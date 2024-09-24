package slide_window;

public class Solution11ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left=0, right=height.length-1;
        int vol=0;
        while(left<right){
            vol=Math.max(vol,(right-left)*Math.min(height[left], height[right]));
            if (height[left]>height[right]){
                right--;
            }else{
                left++;
            }
        }
        return vol;
    }
}
