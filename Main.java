import java.util.Scanner;

class Node {
    int rollNumber;
    Node left, right;

    public Node(int item) {
        rollNumber = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int rollNumber) {
        root = insertRec(root, rollNumber);
        System.out.println("Roll Number " + rollNumber + " inserted successfully.");
    }

    Node insertRec(Node root, int rollNumber) {
        if (root == null) {
            root = new Node(rollNumber);
            return root;
        }
        if (rollNumber < root.rollNumber) {
            root.left = insertRec(root.left, rollNumber);
        } else if (rollNumber > root.rollNumber) {
            root.right = insertRec(root.right, rollNumber);
        } else {
            System.out.println("Roll Number " + rollNumber + " already exists.");
        }
        return root;
    }

    boolean search(int rollNumber) {
        return searchRec(root, rollNumber);
    }

    boolean searchRec(Node root, int rollNumber) {
        if (root == null) {
            return false;
        }
        if (root.rollNumber == rollNumber) {
            return true;
        }
        if (rollNumber < root.rollNumber) {
            return searchRec(root.left, rollNumber);
        }
        return searchRec(root.right, rollNumber);
    }

    void delete(int rollNumber) {
        if (search(rollNumber)) {
            root = deleteRec(root, rollNumber);
            System.out.println("Roll Number " + rollNumber + " deleted successfully.");
        } else {
            System.out.println("Roll Number " + rollNumber + " not found in the BST.");
        }
    }

    Node deleteRec(Node root, int rollNumber) {
        if (root == null) {
            return root;
        }
        if (rollNumber < root.rollNumber) {
            root.left = deleteRec(root.left, rollNumber);
        } else if (rollNumber > root.rollNumber) {
            root.right = deleteRec(root.right, rollNumber);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.rollNumber = minValue(root.right);
            root.right = deleteRec(root.right, root.rollNumber);
        }
        return root;
    }

    int minValue(Node root) {
        int minVal = root.rollNumber;
        while (root.left != null) {
            minVal = root.left.rollNumber;
            root = root.left;
        }
        return minVal;
    }

    void inorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Inorder Traversal: ");
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.rollNumber + " ");
            inorderRec(root.right);
        }
    }

    void preorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Preorder Traversal: ");
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.rollNumber + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void postorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Postorder Traversal: ");
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.rollNumber + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Roll Number BST Menu ---");
            System.out.println("1. Insert Roll Number");
            System.out.println("2. Search Roll Number");
            System.out.println("3. Delete Roll Number");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter roll number to insert: ");
                    int insertVal = scanner.nextInt();
                    bst.insert(insertVal);
                    break;
                case 2:
                    System.out.print("Enter roll number to search: ");
                    int searchVal = scanner.nextInt();
                    if (bst.search(searchVal)) {
                        System.out.println("Roll Number " + searchVal + " is found in the BST.");
                    } else {
                        System.out.println("Roll Number " + searchVal + " is NOT found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter roll number to delete: ");
                    int deleteVal = scanner.nextInt();
                    bst.delete(deleteVal);
                    break;
                case 4:
                    bst.inorder();
                    break;
                case 5:
                    bst.preorder();
                    break;
                case 6:
                    bst.postorder();
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
